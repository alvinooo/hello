package com.example.alvinheng.hello

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText

class InputForm(context: Context, formTitle: String, action: (text: String) -> Unit) {

    init {
        val formBuilder = AlertDialog.Builder(context)
        val formLayout = LayoutInflater.from(context).inflate(R.layout.input_form, null)
        val formView = formLayout.findViewById(R.id.name_field) as EditText

        formBuilder.setTitle(formTitle)
        formBuilder.setView(formLayout)

        formBuilder.setPositiveButton(formTitle) { dialog, _ ->
            val itemName = formView.text.toString()
            action(itemName)
            dialog.dismiss()
        }.setNegativeButton(context.getText(R.string.form_cancel_button_text)) { dialog, _ ->
            dialog.dismiss()
        }
        formBuilder.show()
    }
}