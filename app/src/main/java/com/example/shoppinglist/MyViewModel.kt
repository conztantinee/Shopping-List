package com.example.shoppinglist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.shoppinglist.db.ItemEntity

class MyViewModel (private val repo: Repository): ViewModel() {
    fun insert(item: ItemEntity) = GlobalScope.launch {
        repo.insert(item)
    }

    fun update(item: ItemEntity) = GlobalScope.launch {
        repo.update(item)
    }
    fun delete(item: ItemEntity) = GlobalScope.launch {
        repo.delete(item)
    }



    fun allItems() = repo.allItems()
    }
