package com.akshat.notesapp.repository

import androidx.lifecycle.LiveData
import com.akshat.notesapp.data.dao.Note
import com.akshat.notesapp.data.dao.NotesDao

class NotesRepository(private val notesDao: NotesDao){

    val allNotes: LiveData<ArrayList<Note>> = notesDao.getAllItems()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

}