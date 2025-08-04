package com.esul.esulrotas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.esul.esulrotas.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var torreDataLoader: TorreDataLoader
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    
    private var torreEncontrada: Torre? = null
    private var latitudeAtual: Double = 0.0
    private var longitudeAtual: Double = 0.0
    
    // Launcher para seleção de arquivo
    private val filePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { carregarDadosDaPlanilha(it) }
    }
    
    // Launcher para permissão de localização
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        
        if (fineLocationGranted || coarseLocationGranted) {
            obterLocalizacaoAtual()
        } else {
            Toast.makeText(this, getString(R.string.erro_permissao_localizacao), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupComponents()
        setupListeners()
        verificarPermissoes()
    }
    
    private fun setupComponents() {
        torreDataLoader = TorreDataLoader(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        
        // Inicialmente esconde o card de informações da torre
        binding.cardInfoTorre.isVisible = false
        
        // Status inicial
        binding.tvStatus.text = "Carregue um arquivo GPX para começar"
    }
    
    private fun setupListeners() {
        binding.btnBuscarTorre.setOnClickListener {
            buscarTorre()
        }
        
        binding.btnCarregarDados.setOnClickListener {
            selecionarArquivoPlanilha()
        }
        
        binding.btnVerMapa.setOnClickListener {
            abrirMapa()
        }
    }
    
    private fun verificarPermissoes() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
            != PackageManager.PERMISSION_GRANTED) {
            locationPermissionLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        } else {
            obterLocalizacaoAtual()
        }
    }
    
    private fun obterLocalizacaoAtual() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
            != PackageManager.PERMISSION_GRANTED && 
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) 
            != PackageManager.PERMISSION_GRANTED) {
            return
        }
        
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                latitudeAtual = it.latitude
                longitudeAtual = it.longitude
            }
        }
    }
    
    private fun selecionarArquivoPlanilha() {
        filePickerLauncher.launch("application/gpx+xml")
    }
    
    private fun carregarDadosDaPlanilha(uri: Uri) {
        binding.tvStatus.text = "Carregando dados do GPX..."
        
        torreDataLoader.carregarDeGPX(uri)
            .onSuccess { torres ->
                val quantidade = torres.size
                binding.tvStatus.text = "Dados carregados: $quantidade torres"
                Toast.makeText(this, 
                    "${getString(R.string.sucesso_dados_carregados)}: $quantidade torres", 
                    Toast.LENGTH_SHORT).show()
            }
            .onFailure { erro ->
                binding.tvStatus.text = "Erro ao carregar dados"
                Toast.makeText(this, 
                    "${getString(R.string.erro_carregamento_dados)}: ${erro.message}", 
                    Toast.LENGTH_LONG).show()
            }
    }
    
    private fun buscarTorre() {
        val numeroTorre = binding.etNumeroTorre.text.toString().trim()
        
        if (numeroTorre.isEmpty()) {
            Toast.makeText(this, getString(R.string.erro_numero_invalido), Toast.LENGTH_SHORT).show()
            return
        }
        
        val torre = torreDataLoader.buscarTorrePorNumero(numeroTorre)
        
        if (torre != null) {
            torreEncontrada = torre
            mostrarInformacoesTorre(torre)
        } else {
            Toast.makeText(this, getString(R.string.erro_torre_nao_encontrada), Toast.LENGTH_SHORT).show()
            binding.cardInfoTorre.isVisible = false
        }
    }
    
    private fun mostrarInformacoesTorre(torre: Torre) {
        val distancia = if (latitudeAtual != 0.0 && longitudeAtual != 0.0) {
            torre.calcularDistanciaKm(latitudeAtual, longitudeAtual)
        } else {
            0.0
        }
        
        val infoText = buildString {
            append("Torre: ${torre.numero}\n")
            append("Linha: ${torre.linha}\n")
            append("KM: ${torre.km}\n")
            if (distancia > 0) {
                append("Distância: ${String.format("%.2f", distancia)} km\n")
            }
            append("Coordenadas: ${String.format("%.6f", torre.latitude)}, ${String.format("%.6f", torre.longitude)}")
            if (torre.tipo.isNotEmpty()) {
                append("\nTipo: ${torre.tipo}")
            }
            if (torre.observacoes.isNotEmpty()) {
                append("\nObs: ${torre.observacoes}")
            }
        }
        
        binding.tvInfoTorre.text = infoText
        binding.cardInfoTorre.isVisible = true
    }
    
    private fun abrirMapa() {
        torreEncontrada?.let { torre ->
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra("torre_numero", torre.numero)
                putExtra("torre_linha", torre.linha)
                putExtra("torre_latitude", torre.latitude)
                putExtra("torre_longitude", torre.longitude)
                putExtra("usuario_latitude", latitudeAtual)
                putExtra("usuario_longitude", longitudeAtual)
            }
            startActivity(intent)
        }
    }
}
