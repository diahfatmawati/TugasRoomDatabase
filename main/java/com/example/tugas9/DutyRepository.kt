package com.example.tugas9

import androidx.lifecycle.LiveData

class DutyRepository (private val dutyDao: DutyDao) {
    val allWords: LiveData<List<Duty>> = dutyDao.getAlphabetizeWords()
    suspend fun insert(duty: Duty) {
        dutyDao.insert(duty)
    }
    suspend fun deleteAll(){
        dutyDao.deleteAll()
    }
}