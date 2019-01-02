package com.example.alvinheng.hello

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.radio_button.view.*

class ItemAdapter(val context: Context, val itemViewModel: ItemViewModel) : RecyclerView
.Adapter<RadioButtonHolder>() {

    private var items = ArrayList<Item>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioButtonHolder {
        return RadioButtonHolder(
                LayoutInflater.from(context).inflate(R.layout.radio_button, parent, false))
    }

    override fun onBindViewHolder(holder: RadioButtonHolder, position: Int) {
        holder.radioButton.text = items[position].toString()
        holder.radioButton.setOnClickListener {
            InputForm(context, context.getString(R.string.form_title_edit_item),
                    fun(itemName: String) { renameItem(items[position], itemName) })
        }
    }

    fun appendItem(itemName: String) {
        val item = Item(index = items.size, name = itemName)
        itemViewModel.appendItem(item)
    }

    fun renameItem(item: Item, itemName: String) {
        item.name = itemName
        itemViewModel.updateItem(item)
    }

    fun deleteItemAtPosition(position: Int) {
        val item = getItemAtPosition(position)
        itemViewModel.deleteItem(item)

        // Shift all indices below deleted item up by 1
        for (i in position..items.size - 1) {
            val nextItem = items[i]
            nextItem.index -= 1
            itemViewModel.updateItem(nextItem)
        }
    }

    fun getItemAtPosition(position: Int): Item {
        return items[position]
    }

    fun setItems(list: ArrayList<Item>) {
        items = list
        notifyDataSetChanged()
    }

    fun syncDatabase() {
        for (i in 0 until items.size) items[i].index = i
        itemViewModel.updateItem(*(items.toArray<Item>(arrayOfNulls<Item>(items.size))))
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