package com.example.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = arrayOf(ItemEntity::class), version = 2)
abstract class RoomDBapp: RoomDatabase() {
    abstract fun itemDAO(): ItemDAO?

    companion object{
        private var INSTANCE: RoomDBapp?=null



        val migration_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
            }
        }
        fun getAppDatabase(context: Context): RoomDBapp? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<RoomDBapp>(
                    context.applicationContext, RoomDBapp::class.java, "AppDBB"
                )

                    .fallbackToDestructiveMigration()
                    .build()

            }
            return INSTANCE!!
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}