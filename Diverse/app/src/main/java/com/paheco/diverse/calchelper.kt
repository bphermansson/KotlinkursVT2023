package com.paheco.diverse

class CalcHelper {
    var allnumbers = mutableListOf<String>()
    fun loadNumbers(){
        allnumbers.add("zero")
        allnumbers.add("one")
        allnumbers.add("two")
        allnumbers.add("three")
        allnumbers.add("four")
    }

    fun numberToInt(number: Int) : String {
        return allnumbers[number]
    }
    fun textToNumber(number : String) : Int {
        var result = 0
        when(number.lowercase()) {
            "zero" -> result = 0
            "one" -> result = 1
            "two" -> result = 2
            "three" -> result = 3
            "four" -> result = 4
            "five" -> result = 5
            "six" -> result = 6
            "seven" -> result = 7
            "eight" -> result = 8
            "nine" -> result = 9
        }
        return result
    }
    fun numberToText(number : Int) : String {
        var result = ""
        when(number) {
            0-> result = "zero"
            1-> result = "one"
            2-> result = "two"
            3-> result = "three"
            4-> result = "four"
            5-> result = "five"
            6-> result = "six"
            7-> result = "seven"
            8-> result = "eight"
            9-> result = "nine"
        }
        return result
    }
}