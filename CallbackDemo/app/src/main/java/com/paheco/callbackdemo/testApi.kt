package com.paheco.callbackdemo

class testApi {
    fun testCallback(callback: (String) -> Unit) {
        callback("OK")
    }
}