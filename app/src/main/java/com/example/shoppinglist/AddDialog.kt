package com.example.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.db.ItemEntity
import kotlinx.android.synthetic.main.dialog.*

class AddDialog(context: Context, var Listener: Dialog): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog)
        val categories = context.resources.getStringArray(R.array.categories)
        val adapter = ArrayAdapter(this.context!!,android.R.layout.simple_spinner_item,categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        Save.setOnClickListener{
            if(!checkForEmptyFields())
                if(checkAllFieldsValid()){

            val name = namefield.text.toString()
            val description = descriptionfield.text.toString()
            val price = pricefield.text.toString().toInt()
            var bought = checkBox.isChecked
            val category = spinner.selectedItem.toString()

            var newItem = ItemEntity(name,description,category, price.toLong(),bought)
            Listener.onSaveButtonClicked(newItem)
            dismiss()
        }}
        Cancel.setOnClickListener{cancel()}

    }

    fun checkAllFieldsValid(): Boolean{
        var fieldsAreValid = true
        if(pricefield.text.toString().toInt()<0){
            pricefield.error = "Please, insert a valid price*"
            fieldsAreValid=false
        }
        return fieldsAreValid
    }

    fun checkForEmptyFields(): Boolean{
        var emptyFound = false
        if(namefield.text.isEmpty()){
            namefield.error = "Please, add a name*"
            emptyFound=true
        }
        if(pricefield.text.isEmpty()){
            pricefield.error = "Please, add price*"
            emptyFound=true
        }
        return emptyFound
    }
    }
