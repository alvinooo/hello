package com.example.alvinheng.hello

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class CrudListActivity : AppCompatActivity() {

    lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ItemAdapter(this)

        randomList.layoutManager = LinearLayoutManager(this)
        randomList.adapter = adapter

        val callback = DragAdapter(
            adapter, this, ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
            ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(randomList)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        itemViewModel.allItems.observe(this, Observer { items ->
            items?.let { adapter.setItems(ArrayList(it)) }
        })

        addItem.setOnClickListener { Form(getString(R.string.form_title_add_item), this, adapter, itemViewModel) }
    }
}

