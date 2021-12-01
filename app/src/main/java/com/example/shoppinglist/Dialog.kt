package com.example.shoppinglist

import com.example.shoppinglist.db.ItemEntity

interface Dialog {
    fun onSaveButtonClicked(item: ItemEntity)
}