package com.example.pia11mndag16jan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    var people = mutableListOf<String>("Arne", "Bertil", "Ceasar", "David")
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val personName : TextView
        init {
            personName = view.findViewById(R.id.personTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("pia11debug", "Skapa rad")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("pia11debug", "RITA RAD " + position.toString())
        holder.personName.text = people[position]
    }

    override fun getItemCount(): Int {
        return people.size
    }
}