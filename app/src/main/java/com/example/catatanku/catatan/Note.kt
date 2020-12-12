package com.example.catatanku.catatan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table") //nama tabel
data class Note(
    var title: String, //membuat kolom title
    var description: String, // membuat kolom deskripsi
    var priority: Int //membuat kolom priority
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //primary key
}