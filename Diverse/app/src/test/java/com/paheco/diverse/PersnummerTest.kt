package com.paheco.diverse

import org.junit.Assert.*

import org.junit.Test

class PersnummerTest {

    @Test
    fun personnummerChecker() {
        var chelp = CalcHelper()
        assertEquals(false, chelp.personnummerChecker(""))
        assertEquals(true, chelp.personnummerChecker("860101-3983"))
        assertEquals(true, chelp.personnummerChecker("920826+6834"))
        assertEquals(true, chelp.personnummerChecker("5302216972"))
        assertEquals(false, chelp.personnummerChecker("431105-7777"))
        assertEquals(false, chelp.personnummerChecker("050403-1857"))
        assertEquals(false, chelp.personnummerChecker("20050403-1857"))

    }
}