package com.akshat.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.akshat.notesapp.dao.Note
import com.akshat.notesapp.db.NotesDB
import com.akshat.notesapp.repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    var allNotes: LiveData<ArrayList<Note>>

    init {
        val dao = NotesDB.getDatabase(application).notesDao()
        val db = NotesRepository(dao)
        allNotes = db.allNotes
    }
}