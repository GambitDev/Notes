package com.gambitdev.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "notes")
class Note {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "title")
    private String title;

    @ColumnInfo (name = "content")
    private String content;

    @ColumnInfo (name = "timestamp")
    private String timestamp;

    Note(String title, String content , String timestamp) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    String getTimestamp() {
        return timestamp;
    }

    void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


