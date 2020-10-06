package com.example.homework6.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_list")
data class ContactItem (
        @ColumnInfo(name="name") var name: String?,
        @ColumnInfo(name="data") var data: String?,
        @ColumnInfo(name="is_phone") var isPhone: Boolean
){
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}