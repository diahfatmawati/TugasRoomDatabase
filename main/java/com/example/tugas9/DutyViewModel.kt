package com.example.tugas9

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class DutyViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: DutyRepository
    // LiveData gives us update words when they change.
    val allWords: LiveData<List<Duty>>
    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository
        val dutysDao = DutyRoomDatabase.getDatabase(application, viewModelScope).dutyDao()
        repository = DutyRepository(dutysDao)
        allWords = repository.allWords
    }
    // untuk memasukkan data
    fun insert(duty: Duty) = viewModelScope.launch {
        repository.insert(duty)    }
    // untuk hapus semua data tugas
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()    }
}