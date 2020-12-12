package com.example.catatanku.catatan

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert //memasukkan note ke db
    fun insert(note: Note)
    @Update //update note
    fun update(note: Note)
    @Delete //menghapus note
    fun delete(note: Note)
    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>
}
