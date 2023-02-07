package com.paheco.modernadapter

class ViewHolder(
    itemView: View,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnClickListener {
            // this will be called only once.
            onItemClicked(bindingAdapterPosition)
        }
    }

    fun bind(model: Model) {
        //bind data with the component
    }
}