package com.example.catatanku.catatan

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(application: Application) {
    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }
    //menambahkan insert note dari noteDao ke repository
    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }
    //menambahkan update note dari noteDao ke repository
    fun update(note: Note) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(noteDao).execute(note)
    }
    //menambahkan delete note dari noteDao ke repository
    fun delete(note: Note) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
    //eksekusi AsyncTask
    companion object {
        private class InsertNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
            val noteDao = noteDao
            override fun doInBackground(vararg p0: Note?) {
                noteDao.insert(p0[0]!!)
            }
        }

        private class UpdateNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
            val noteDao = noteDao
            override fun doInBackground(vararg p0: Note?) {
                noteDao.update(p0[0]!!)
            }
        }

        private class DeleteNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
            val noteDao = noteDao
            override fun doInBackground(vararg p0: Note?) {
                noteDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllNotesAsyncTask(noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {
            val noteDao = noteDao
            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllNotes()
            }
        }
    }
}