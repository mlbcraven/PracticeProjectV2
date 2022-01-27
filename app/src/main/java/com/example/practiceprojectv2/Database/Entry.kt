package com.example.practiceprojectv2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry(
    @PrimaryKey(autoGenerate = true) var ID : Int = 0,
    @ColumnInfo(name = "Worker") var name :String?,
    @ColumnInfo(name = "Worklist") var work: String?,
    @ColumnInfo(name = "Instance") var time : String?
)
