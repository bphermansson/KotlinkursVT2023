package com.paheco.contactlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val contactName : TextView
        val contactPhone : TextView

        init {
            contactName = view.findViewById(R.id.contactNameTV)
            contactPhone = view.findViewById(R.id.contactPhoneTV)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("pia11debug", "SKAPA RAD")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("pia11debug", "RITA RAD " + position.toString())

        holder.contactName.text = "Patrik " + position.toString()
        holder.contactPhone.text = "Teflon: 123321"
    }

    override fun getItemCount(): Int {
        return 500
    }

}