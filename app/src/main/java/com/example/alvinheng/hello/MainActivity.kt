package com.example.alvinheng.hello

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomList.layoutManager = LinearLayoutManager(this)
        randomList.adapter = TaskAdapter(ArrayList((1..10).toList()), this)
    }
}
