package com.example.shoppinglist

import com.example.shoppinglist.db.ItemEntity
import com.example.shoppinglist.db.RoomDBapp

class Repository(private val db: RoomDBapp) {
    suspend fun insert(item: ItemEntity) = db.itemDAO()?.insertItem(item)
    suspend fun update(item: ItemEntity) = db.itemDAO()?.updateItem(item)
    suspend fun delete(item: ItemEntity) = db.itemDAO()?.deleteItem(item)


    fun allItems() = db.itemDAO()?.getAllItemInfo()
}