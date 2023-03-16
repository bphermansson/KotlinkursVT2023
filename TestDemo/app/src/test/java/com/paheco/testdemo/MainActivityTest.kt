package com.paheco.testdemo

import org.junit.Assert.*

import org.junit.Test

class MainActivityTest {

    @Test
    fun onCreate() {
    }

    @Test
    fun fancyFunctionTest() {
        var testHelp = mathFunctions()
        assertEquals(6, testHelp.doublerFunction(3))
    }
}