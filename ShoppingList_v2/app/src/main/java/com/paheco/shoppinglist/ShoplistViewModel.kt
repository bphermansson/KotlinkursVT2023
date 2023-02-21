package com.paheco.shoppinglist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ShoplistViewModel : ViewModel() {
    var shopitems = mutableListOf<ShoppingItem>()
    val currentName: MutableLiveData<List<ShoppingItem>> by lazy {
        MutableLiveData<List<ShoppingItem>>()
    }

    fun addShopping(addshopname : String, addshopamount : String {
        if(addshopname == "") {
            errorMessage.value = "Ange text"
            return
        }
        val shopamount = addshopamount.toIntOrNull()
        if(shopamount == null) {
            errorMessage.value = "Ange ett antal"
            return
        }
        errorMe
        val tempShopitem = ShoppingItem(addshopname, addshopamount)
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)
        shopRef.push().setValue(tempShopitem).addOnCompleteListener {
            loadShopping()
        }
    }

    fun loadShopping() {
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)
        shopRef.get().addOnSuccessListener {
            val shoplist = mutableListOf<ShoppingItem>()
            it.children.forEach { childsnap ->
                val tempShop = childsnap.getValue<ShoppingItem>()!!
                tempShop.fbid = childsnap.key
                shoplist.add(tempShop)
            }
            shopitems = shoplist
        }

    }

    fun deleteShop(delitem : ShoppingItem, callback: () -> Unit) {
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping").child(Firebase.auth.currentUser!!.uid)

        shopRef.child(delitem.fbid!!).removeValue().addOnCompleteListener {
            loadShopping()
        }
    }
    fun doneShop(doneitem: ShoppingItem, isdone: Boolean) {
        val database = Firebase.database
        val shopRef = database.getReference("androidshopping")
        var saveitem = doneitem
        saveitem.shopdone = isdone
        shopRef.child(doneitem.fbid!!).setValue(saveitem).addOnCompleteListener() {
            //loadShopping()
        }
    }

}