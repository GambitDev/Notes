package com.gambitdev.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNote(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
    void deleteNote(int id);

    @Query("UPDATE notes SET title = :title , content = :content , timestamp = :timestamp WHERE id = :id")
    void updateNote(int id , String title , String content , String timestamp);

    @Query("DELETE FROM notes")
    void deleteAll();
}
