package com.example.tugas9

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "duty_table")
data class Duty (@PrimaryKey @ColumnInfo(name = "duty") val duty: String)