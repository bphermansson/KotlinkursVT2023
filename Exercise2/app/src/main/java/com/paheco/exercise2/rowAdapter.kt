package com.paheco.exercise2

import android.content.Context
import android.graphics.ColorSpace
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RowAdapter (
                  private val cellClickListener: CellClickListener
    ) : RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    val numbers = listOf(1, 3, 9, 16, 25)

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var rowTV : TextView
        init {
            rowTV = view.findViewById(R.id.rowTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rowTV.text = position.toString() + " - " + numbers[position].toString()
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener()
        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }
}