package com.example.alvinheng.hello

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class DragAdapter(adapter: TaskAdapter, context: Context, dragDirs: Int,
                  swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    var adapter = adapter

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        adapter.swapItems(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT) {
            adapter.removeItem(viewHolder.adapterPosition)
        } else {
            adapter.removeItem(viewHolder.adapterPosition)
        }
    }
}