package com.paheco.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.magictechnology.pia11shopping.ShoppingFragment

class ShoppingAdapter : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
        var shopitems = mutableListOf<ShoppingItem>()

        lateinit var frag : ShoppingFragment

        class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val shoppingName : TextView
            val shoppingDelete: ImageView
            val shoppingChecked: CheckBox

            init {
                shoppingName = view.findViewById(R.id.shopNameTV)
                shoppingDelete = view.findViewById(R.id.shopDeleteImg)
                shoppingChecked = view.findViewById(R.id.shopChecked)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val currentShop = frag.model.shopitems.size

            holder.shoppingName.text = shopitems[position].shopname
            println("---" + shopitems[position].shopname)

            holder.shoppingDelete.setOnClickListener{
                frag.model.deleteShop(currentshop)
            }

            holder.shoppingChecked.isChecked = false
            currentShop.shopdone?.let{
                holder.shoppingChecked.isChecked = it
            }

            holder.shoppingChecked.setOnCheckChangedListener { compoundButton, shopChecked ->
                frag.model.doneShop(currentShop, holder.shoppingChecked.isChecked)
            }
        }




        override fun getItemCount(): Int {
            return shopitems.size
        }
    }