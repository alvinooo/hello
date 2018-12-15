package com.example.alvinheng.hello

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList((1..5).toList().map {it.toString()})

        val adapter = TaskAdapter(list, this)
        randomList.layoutManager = LinearLayoutManager(this)
        randomList.adapter = adapter

        val callback = DragAdapter(adapter, this, ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
                                   ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT))
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(randomList)

        addItem.setOnClickListener {extendList(list, adapter)}
    }

    fun extendList(list: ArrayList<String>, adapter: TaskAdapter) {
        Form(getString(R.string.form_title_add_item), this, adapter)
//        list.add(list.size + 1)
//        adapter.notifyItemChanged(list.size - 1)
    }
}
