package com.akshat.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akshat.notesapp.dao.Note
import com.akshat.notesapp.db.NotesDB
import com.akshat.notesapp.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    var allNotes: LiveData<ArrayList<Note>>
    val notesRepository: NotesRepository

    init {
        val dao = NotesDB.getDatabase(application).notesDao()
        notesRepository = NotesRepository(dao)
        allNotes = notesRepository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.delete(note)
    }
}