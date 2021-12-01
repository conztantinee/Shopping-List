package com.example.shoppinglist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@kotlinx.serialization.Serializable
@Entity(tableName = "iteminfo")
data class ItemEntity  (

    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name="category") val category: String,
    @ColumnInfo(name="price") val price : Long,
    @ColumnInfo(name="bought") var bought : Boolean,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0
): Serializable

