package com.paheco.exercise2

import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyRowAdapter(
    private val items: List<ContentItem>,
    private val listener: (ContentItem) -> Unit
    //private val items: List<String>,
    //private val listener: (String) -> Unit
) : RecyclerView.Adapter<MyRowAdapter.ViewHolder>() {
    var myFruits = mutableListOf("Apple", "Banana")

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var rowTV : TextView
        init {
            rowTV = view.findViewById(R.id.rowTV)
        }
        //private val name = itemView.findViewById<TextView>(R.id.name)
        //private val image = itemView.findViewById<ImageView>(R.id.image)
        fun bind(item: ContentItem) {
            //name.text = item.name
            //image.loadUrl(item.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("ExerciseLog", "RITA RAD " + position.toString())
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
        /*
        holder.rowTV.text = myFruits[position]
        holder.itemView.setOnClickListener {
            Log.i("ExerciseLog", "CLICK")
            //requireActivity().supportFragmentManager.beginTransaction().add(R.id.appFragmentContainer, detailFragment).addToBackStack("mystack").commit()
        }
         */
    }

    override fun getItemCount(): Int {
        return items.size
    }
}