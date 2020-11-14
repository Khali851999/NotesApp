package com.akshat.notesapp.repository

import androidx.lifecycle.LiveData
import com.akshat.notesapp.dao.Note
import com.akshat.notesapp.dao.NotesDao

class NotesRepository(private val notesDao: NotesDao){

    val allNotes: LiveData<ArrayList<Note>> = notesDao.getAllItems()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

}