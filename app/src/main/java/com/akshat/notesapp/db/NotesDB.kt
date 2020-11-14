package com.akshat.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akshat.notesapp.dao.Note
import com.akshat.notesapp.dao.NotesDao

@Database(entities = [Note::class], version = 1)
abstract class NotesDB: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getDatabase(context: Context): NotesDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            //synchronised acts as mutex lock
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "word_database"
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
