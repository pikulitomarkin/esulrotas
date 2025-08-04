package com.esul.esulrotas

/**
 * Classe que representa uma torre de transmissão
 * Baseada na estrutura do projeto original
 */
data class Torre(
    val numero: String,
    val linha: String,
    val km: Double,
    val latitude: Double,
    val longitude: Double,
    val tipo: String = "",
    val circuito: String = "",
    val observacoes: String = ""
) {
    /**
     * Calcula a distância em metros entre esta torre e uma coordenada
     */
    fun calcularDistancia(lat: Double, lng: Double): Double {
        val earthRadius = 6371000.0 // Raio da Terra em metros
        
        val lat1Rad = Math.toRadians(latitude)
        val lat2Rad = Math.toRadians(lat)
        val deltaLatRad = Math.toRadians(lat - latitude)
        val deltaLngRad = Math.toRadians(lng - longitude)
        
        val a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                Math.sin(deltaLngRad / 2) * Math.sin(deltaLngRad / 2)
        
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        
        return earthRadius * c
    }
    
    /**
     * Calcula a distância em quilômetros
     */
    fun calcularDistanciaKm(lat: Double, lng: Double): Double {
        return calcularDistancia(lat, lng) / 1000.0
    }
    
    /**
     * Retorna informações formatadas da torre
     */
    fun getInfoFormatada(): String {
        return buildString {
            append("Torre: $numero\n")
            append("Linha: $linha\n")
            append("KM: $km\n")
            if (tipo.isNotEmpty()) append("Tipo: $tipo\n")
            if (circuito.isNotEmpty()) append("Circuito: $circuito\n")
            append("Coordenadas: ${String.format("%.6f", latitude)}, ${String.format("%.6f", longitude)}")
            if (observacoes.isNotEmpty()) append("\nObs: $observacoes")
        }
    }
}
