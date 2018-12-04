package com.example.alvinheng.hello

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.radio_button.view.*

class TaskAdapter(val items: ArrayList<Int>, val context: Context) : RecyclerView.Adapter<RadioButtonHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioButtonHolder {
        return RadioButtonHolder(LayoutInflater.from(context).inflate(R.layout.radio_button, parent, false))
    }

    override fun onBindViewHolder(holder: RadioButtonHolder, position: Int) {
        holder.radioButton.text = "wtf: ${items.get(position)}"
    }
}

class RadioButtonHolder(view: View) : RecyclerView.ViewHolder(view) {
    val radioButton = view.radio_button
}