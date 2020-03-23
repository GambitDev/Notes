package com.gambitdev.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class NotesRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    NotesRepository(Application application) {
        NotesDatabase db = NotesDatabase.getDatabase(application);
        noteDao = db.noteDao();
        notes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    void insertNote(Note note) {
        NotesDatabase.databaseWriteExecutor.execute(() ->
                noteDao.insertNote(note));
    }

    void deleteNote(int id) {
        NotesDatabase.databaseWriteExecutor.execute(() ->
                noteDao.deleteNote(id));
    }

    void updateNote(int id , String title , String content) {
        NotesDatabase.databaseWriteExecutor.execute(() ->
                noteDao.updateNote(id , title , content));
    }
}
