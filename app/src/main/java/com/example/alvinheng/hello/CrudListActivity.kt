package com.example.alvinheng.hello

import android.arch.lifecycle.Observer
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

        // App data
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        val adapter = ItemAdapter(this, itemViewModel)
        randomList.layoutManager = LinearLayoutManager(this)
        randomList.adapter = adapter
        itemViewModel.allItems.observe(this, Observer { items ->
            items?.let { adapter.setItems(ArrayList(it)) }
        })

        // User gestures
        val callback =
                DragAdapter(adapter, ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
                        ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
                )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(randomList)

        addItem.setOnClickListener {
            InputForm(this,
                    getString(R.string.form_title_add_item),
                    { itemName: String -> adapter.appendItem(itemName) })
        }
    }
}

