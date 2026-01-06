package com.example.mininavigationapp.utils

object NavigationFormatter {
    fun formatDistance(meters: Double): String {
        return if (meters >= 1000) {
            "${String.format("%.1f", meters / 1000)} km"
        } else {
            "${meters.toInt()} m"
        }
    }
    fun formatEta(seconds: Int): String {
        return when {
            seconds < 60 -> "Less than 1 min"
            seconds < 3600 -> {
                val mins = seconds / 60
                "$mins min"
            }
            else -> {
                val hours = seconds / 3600
                val mins = (seconds % 3600) / 60
                if (mins == 0) "${hours}h" else "${hours}h ${mins}m"
            }
        }
    }
}