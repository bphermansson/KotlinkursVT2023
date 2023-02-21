package com.paheco.exercise2_take2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    val onItemClicked : (Boolean)-> Unit)
    : RecyclerView.Adapter<PersonAdapter.ViewHolder>(){

        class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val personName : TextView
            init {
                personName = view.findViewById(R.id.personNameTV)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.personname, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.personName.text = "Bertil " + position.toString()
            holder.itemView.setOnClickListener {
                onItemClicked(true)
            }
        }

        override fun getItemCount(): Int {
            return 5
        }
}