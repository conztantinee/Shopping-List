package com.example.shoppinglist.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDAO {

 @Query("SELECT * FROM iteminfo ORDER BY id DESC")
 fun getAllItemInfo(): LiveData<List<ItemEntity>>

 @Insert
 fun insertItem(item: ItemEntity?)

 @Delete
 fun deleteItem(item: ItemEntity?)

 @Update
 fun updateItem(item: ItemEntity?)

}