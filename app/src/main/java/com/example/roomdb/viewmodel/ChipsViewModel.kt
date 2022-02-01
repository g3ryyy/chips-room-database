package com.example.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.roomdb.data.ChipsDatabase
import com.example.roomdb.repository.ChipsRepository
import com.example.roomdb.model.Chips
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChipsViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Chips>>
    private val repository: ChipsRepository

    init {
        val chipsDao = ChipsDatabase.getDatabase(application).chipsDao()
        repository = ChipsRepository(chipsDao)
        readAllData = repository.readAllData
    }

    fun addChips(chips: Chips) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChips(chips)
        }
    }

    fun updateChips(chips: Chips) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateChips(chips)
        }
    }

    fun deleteChips(chips: Chips) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChips(chips)
        }
    }

    fun deleteAllChips() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllChips()
        }
    }

   /* fun searchQuery(searchQuery: String): LiveData<List<Chips>>{
        return repository.searchDatabase(searchQuery).asFlow()
    }*/
}