package com.gambitdev.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NotesRepository repository;
    private LiveData<List<Note>> notes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        notes = repository.getNotes();
    }

    LiveData<List<Note>> getNotes() {
        return notes;
    }

    void insertNote(Note note) {
        repository.insertNote(note);
    }

    void deleteNote(int id) {
        repository.deleteNote(id);
    }

    void updateNote(int id , String title , String content , String timestamp) {
        repository.updateNote(id , title , content , timestamp);
    }
}
