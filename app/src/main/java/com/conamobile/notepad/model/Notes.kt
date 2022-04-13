package com.conamobile.notepad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
class Notes : Serializable {

    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "notes")
    var note: String = ""

    @ColumnInfo(name = "date")
    var date: String = ""

    @ColumnInfo(name = "pinned")
    var pinned: Boolean = false
}