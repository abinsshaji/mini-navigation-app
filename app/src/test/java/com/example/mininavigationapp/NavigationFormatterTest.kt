package com.example.mininavigationapp

import com.example.mininavigationapp.utils.NavigationFormatter
import org.junit.Test

class NavigationFormatterTest {
    @Test
    fun testEtaFormatting() {
        assert(NavigationFormatter.formatEta(45) == "Less than 1 min")
        assert(NavigationFormatter.formatEta(120) == "2 min")
        assert(NavigationFormatter.formatEta(3660) == "1h 1m")
    }
}