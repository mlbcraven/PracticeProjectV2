package com.example.practiceprojectv2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entry::class], version = 2)
abstract class EntryDatabase: RoomDatabase() {
    abstract fun entryDao() : EntryDao?
    companion object {
        private var INSTANCE: EntryDatabase? = null

        fun getAppDatabase(context: Context): EntryDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<EntryDatabase>(
                    context.applicationContext, EntryDatabase::class.java, "Database_name"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}