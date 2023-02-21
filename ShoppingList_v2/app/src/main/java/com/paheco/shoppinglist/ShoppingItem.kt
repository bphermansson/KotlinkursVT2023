package com.paheco.shoppinglist

data class ShoppingItem(val shopname : String? = null, val shopamount : Int? = null, var shopdone : Boolean? = null) {
    var fbid : String? = null
}