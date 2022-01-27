package com.example.practiceprojectv2.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Query("Select * from Entry")
    fun getAll(): List<Entry>

    @Query("Select * from Entry where Id IN (:entryIds)")
    fun loadAllByIds(entryIds: IntArray): List<Entry>

   // @Query("Select * from Entry where Worker like : worker and Work like : work Limit 5")
    // fun findByName(worker: String, work:String)

    @Insert
    fun insertAll(vararg workers: Entry)

    @Delete
    fun DeleteEntry(entry : Entry)
}