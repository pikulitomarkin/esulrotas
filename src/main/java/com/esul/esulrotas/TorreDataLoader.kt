package com.esul.esulrotas

import android.content.Context
import android.net.Uri
import io.jenetics.jpx.GPX
import io.jenetics.jpx.WayPoint
import java.io.InputStream

/**
 * Classe responsável por carregar dados das torres a partir de arquivos GPX
 * Adaptada para ler waypoints de arquivos GPX ao invés de planilhas Excel
 */
class TorreDataLoader(private val context: Context) {
    
    private val torres = mutableListOf<Torre>()
    
    /**
     * Carrega torres de um arquivo GPX
     * Os waypoints devem conter as informações da torre no nome e descrição
     * Formato esperado do waypoint:
     * - name: Número da Torre (ex: "T001")
     * - desc: Informações adicionais separadas por | (ex: "LT 138kV|12.5|Suspensão|Circuito 1|Obs")
     */
    fun carregarDeGPX(uri: Uri): Result<List<Torre>> {
        return try {
            val inputStream: InputStream = context.contentResolver.openInputStream(uri)
                ?: return Result.failure(Exception("Não foi possível abrir o arquivo GPX"))
            
            val gpx = GPX.read(inputStream)
            val torresList = mutableListOf<Torre>()
            
            // Processa waypoints do GPX
            gpx.wayPoints.forEach { waypoint ->
                try {
                    val torre = processarWaypoint(waypoint)
                    if (torre != null) {
                        torresList.add(torre)
                    }
                } catch (e: Exception) {
                    // Ignora waypoint com erro e continua
                    continue
                }
            }
            
            // Processa waypoints de tracks se houver
            gpx.tracks.forEach { track ->
                track.segments.forEach { segment ->
                    segment.points.forEach { trackPoint ->
                        try {
                            val torre = processarTrackPoint(trackPoint)
                            if (torre != null) {
                                torresList.add(torre)
                            }
                        } catch (e: Exception) {
                            // Ignora ponto com erro e continua
                            continue
                        }
                    }
                }
            }
            
            inputStream.close()
            
            torres.clear()
            torres.addAll(torresList)
            
            Result.success(torresList)
            
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Processa um waypoint e converte em Torre
     */
    private fun processarWaypoint(waypoint: WayPoint): Torre? {
        val numero = waypoint.name.orElse("").trim()
        if (numero.isEmpty()) return null
        
        val latitude = waypoint.latitude.toDegrees()
        val longitude = waypoint.longitude.toDegrees()
        
        // Parse da descrição (formato: linha|km|tipo|circuito|observacoes)
        val descricao = waypoint.description.orElse("").trim()
        val partes = if (descricao.isNotEmpty()) descricao.split("|") else emptyList()
        
        val linha = partes.getOrElse(0) { "Linha não informada" }
        val km = try {
            partes.getOrElse(1) { "0" }.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
        val tipo = partes.getOrElse(2) { "" }
        val circuito = partes.getOrElse(3) { "" }
        val observacoes = partes.getOrElse(4) { "" }
        
        return Torre(
            numero = numero,
            linha = linha,
            km = km,
            latitude = latitude,
            longitude = longitude,
            tipo = tipo,
            circuito = circuito,
            observacoes = observacoes
        )
    }
    
    /**
     * Processa um track point e converte em Torre (se tiver nome)
     */
    private fun processarTrackPoint(trackPoint: WayPoint): Torre? {
        val numero = trackPoint.name.orElse("").trim()
        if (numero.isEmpty()) return null
        
        return processarWaypoint(trackPoint)
    }
    
    /**
     * Busca uma torre pelo número
     */
    fun buscarTorrePorNumero(numero: String): Torre? {
        return torres.find { it.numero.equals(numero, ignoreCase = true) }
    }
    
    /**
     * Busca torres próximas a uma coordenada (em km)
     */
    fun buscarTorresProximas(latitude: Double, longitude: Double, raioKm: Double): List<Torre> {
        return torres.filter { torre ->
            torre.calcularDistanciaKm(latitude, longitude) <= raioKm
        }.sortedBy { torre ->
            torre.calcularDistanciaKm(latitude, longitude)
        }
    }
    
    /**
     * Retorna todas as torres carregadas
     */
    fun getAllTorres(): List<Torre> = torres.toList()
    
    /**
     * Retorna o número de torres carregadas
     */
    fun getQuantidadeTorres(): Int = torres.size
    
    /**
     * Limpa os dados carregados
     */
    fun limparDados() {
        torres.clear()
    }
}
