package com.example.alvinheng.hello

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText

class Form(form_title: String, context: Context, adapter: TaskAdapter) {
    init {
        val titleMap = HashMap<String, (String) -> Unit>()
        val formBuilder = AlertDialog.Builder(context)
        val formLayout = LayoutInflater.from(context).inflate(R.layout.input_form, null)
        val formView = formLayout.findViewById(R.id.name_field) as EditText

        titleMap.put(context.getString(R.string.form_title_add_item)) {item: String -> adapter.appendItem(item)}
        formBuilder.setTitle(form_title)
        formBuilder.setView(formLayout)

        formBuilder.setPositiveButton(form_title) { dialog, which ->
            titleMap[form_title]!!.invoke(formView.text.toString())
            dialog.dismiss()
        }
        formBuilder.show()
    }
}