package com.example.roomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "chips_table")
data class Chips(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val taste: String
): Parcelable