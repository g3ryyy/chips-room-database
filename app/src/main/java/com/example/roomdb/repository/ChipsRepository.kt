package com.example.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.roomdb.data.ChipsDao
import com.example.roomdb.model.Chips

class ChipsRepository(private val chipsDao: ChipsDao) {

    val readAllData: LiveData<List<Chips>> = chipsDao.readAllData()

    suspend fun addChips(chips: Chips){
        chipsDao.addChips(chips)
    }

    suspend fun updateChips(chips: Chips){
        chipsDao.updateChips(chips)
    }

    suspend fun deleteChips(chips: Chips){
        chipsDao.deleteChips(chips)
    }

    suspend fun deleteAllChips(){
        chipsDao.deleteAllChips()
    }
    //POKA NE RABOTAET
   /* suspend fun searchDatabase(searchQuery: String): LiveData<List<Chips>>{
        return chipsDao.searchDatabase(searchQuery)
    }*/

}