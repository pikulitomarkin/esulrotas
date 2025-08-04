package com.esul.esulrotas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var torreLatitude: Double = 0.0
    private var torreLongitude: Double = 0.0
    private var usuarioLatitude: Double = 0.0
    private var usuarioLongitude: Double = 0.0
    private var torreNumero: String = ""
    private var torreLinha: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Recebe os dados da torre da MainActivity
        intent?.let {
            torreNumero = it.getStringExtra("torre_numero") ?: ""
            torreLinha = it.getStringExtra("torre_linha") ?: ""
            torreLatitude = it.getDoubleExtra("torre_latitude", 0.0)
            torreLongitude = it.getDoubleExtra("torre_longitude", 0.0)
            usuarioLatitude = it.getDoubleExtra("usuario_latitude", 0.0)
            usuarioLongitude = it.getDoubleExtra("usuario_longitude", 0.0)
        }

        // Configura o fragment do mapa
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Torre $torreNumero"
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Configurações do mapa
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true

        // Habilita localização se tiver permissão
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
            == PackageManager.PERMISSION_GRANTED || 
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) 
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }

        // Adiciona marcador da torre
        val torrePosition = LatLng(torreLatitude, torreLongitude)
        val torreMarker = mMap.addMarker(
            MarkerOptions()
                .position(torrePosition)
                .title("Torre $torreNumero")
                .snippet("Linha: $torreLinha")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        // Adiciona marcador da posição do usuário se disponível
        if (usuarioLatitude != 0.0 && usuarioLongitude != 0.0) {
            val usuarioPosition = LatLng(usuarioLatitude, usuarioLongitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(usuarioPosition)
                    .title("Sua posição")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )

            // Desenha linha entre usuário e torre
            val polyline = mMap.addPolyline(
                PolylineOptions()
                    .add(usuarioPosition, torrePosition)
                    .width(5f)
                    .color(Color.BLUE)
                    .pattern(listOf(Dash(10f), Gap(5f)))
            )

            // Calcula os limites para mostrar ambos os pontos
            val boundsBuilder = LatLngBounds.Builder()
            boundsBuilder.include(usuarioPosition)
            boundsBuilder.include(torrePosition)
            val bounds = boundsBuilder.build()

            // Ajusta o zoom para mostrar ambos os pontos
            val padding = 100 // pixels
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
        } else {
            // Se não há posição do usuário, centraliza na torre
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(torrePosition, 15f))
        }

        // Configura info window personalizada
        mMap.setOnInfoWindowClickListener { marker ->
            if (marker.title?.contains("Torre") == true) {
                mostrarOpcoesNavegacao()
            }
        }
    }

    private fun mostrarOpcoesNavegacao() {
        val uri = Uri.parse("google.navigation:q=$torreLatitude,$torreLongitude")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Se Google Maps não estiver instalado, abre no navegador
            val browserUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$torreLatitude,$torreLongitude")
            val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
            startActivity(browserIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
