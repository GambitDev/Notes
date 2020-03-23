package com.gambitdev.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class} , version = 1 , exportSchema = false)
abstract class NotesDatabase extends RoomDatabase {

    private static volatile NotesDatabase instance = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NotesDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (NotesDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDatabase.class,
                            "notes_db")
                            .build();
                }
            }
        }
        return instance;
    }

    abstract NoteDao noteDao();
}
