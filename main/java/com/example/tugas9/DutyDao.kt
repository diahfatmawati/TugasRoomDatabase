package com.example.tugas9

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DutyDao {

    @Query("SELECT * from duty_table ORDER BY duty ASC")
    fun getAlphabetizeWords(): LiveData<List<Duty>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(duty: Duty)

    @Query("DELETE FROM duty_table")
    suspend fun deleteAll()
}