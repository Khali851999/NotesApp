package com.akshat.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akshat.notesapp.data.dao.Note
import com.akshat.notesapp.data.dao.NotesDao
import com.akshat.notesapp.data.db.NotesDB
import com.akshat.notesapp.data.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    var allNotes: LiveData<List<Note>>
    private val notesRepository: NotesRepository

    init {
        val dao: NotesDao = NotesDB.getDatabase(application).notesDao()
        notesRepository = NotesRepository(dao)
        allNotes = notesRepository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.insert(note)
    }
}