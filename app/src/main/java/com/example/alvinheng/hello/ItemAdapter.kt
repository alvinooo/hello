package com.example.alvinheng.hello

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.radio_button.view.*

class ItemAdapter(val context: Context) : RecyclerView.Adapter<RadioButtonHolder>() {

    private var items = ArrayList<Item>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioButtonHolder {
        return RadioButtonHolder(
            LayoutInflater.from(context).inflate(R.layout.radio_button, parent, false))
    }

    override fun onBindViewHolder(holder: RadioButtonHolder, position: Int) {
        holder.radioButton.text =
                context.getString(R.string.list_item_string).format(items.get(position))
    }

    fun setItems(list: ArrayList<Item>) {
        items = list
        notifyDataSetChanged()
    }

    fun appendItem(item: Item) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun editItem(position: Int, item: Item) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                items.set(i, items.set(i + 1, items.get(i)))
            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                items.set(i, items.set(i - 1, items.get(i)))
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
}

class RadioButtonHolder(view: View) : RecyclerView.ViewHolder(view) {
    val radioButton = view.radio_button
}