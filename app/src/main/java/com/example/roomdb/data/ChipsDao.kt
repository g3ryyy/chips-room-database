package com.example.roomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdb.model.Chips
import java.util.concurrent.Flow

@Dao
interface ChipsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChips(chips: Chips)

    @Update
    suspend fun updateChips(chips: Chips)

    @Delete
    suspend fun deleteChips(chips: Chips)

    @Query("DELETE FROM chips_table")
    suspend fun deleteAllChips()


    @Query("SELECT * FROM chips_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Chips>>

    /*@Query("SELECT * FROM chips_table WHERE name LIKE :searchQuery OR taste LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Chips>>*/
}