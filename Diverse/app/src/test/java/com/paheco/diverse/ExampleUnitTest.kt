package com.paheco.diverse

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var chelp = CalcHelper()


    @Before
    fun setUp() {
        chelp.loadNumbers()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun kollatext(){

        assertEquals(0, chelp.textToNumber("zero"))
        assertEquals(1, chelp.textToNumber("one"))
        assertEquals(3, chelp.textToNumber("three"))
        assertEquals(4, chelp.textToNumber("four"))
        /*
        assertEquals(null, chelp.textToNumber("twentynine"))
        assertEquals(null, chelp.textToNumber("banan"))
        assertEquals(null, chelp.textToNumber(""))
         */

    }
    @Test
    fun kollasiffra(){
        assertEquals("zero", chelp.numberToText(0))
        assertEquals("one", chelp.numberToText(1))
        assertEquals("two", chelp.numberToText(2))
        assertEquals("three", chelp.numberToText(3))
        assertEquals(null, chelp.numberToText(798))
        assertEquals(null, chelp.numberToText(-1))
    }
    @Test
    fun kollaresultat() {
        assertEquals("seven", chelp.calcStrings("three", "four"))
        assertEquals("three", chelp.calcStrings("two", "one"))
        assertEquals("five", chelp.calcStrings("three", "two"))
        assertEquals("", chelp.calcStrings("hej", "hopp"))
        //assertEquals("five", chelp.calcStrings("nine", "nine"))
    }
}