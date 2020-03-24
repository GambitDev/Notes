package com.gambitdev.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel viewModel;
    private static final int ADD_NOTE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv);
        NotesAdapter adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getNotes().observe(this, adapter::setNoteList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this , AddNoteActivity.class);
            startActivityForResult(intent , ADD_NOTE_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String noteTitle = data.getStringExtra("title");
                String noteContent = data.getStringExtra("content");
                String noteTimestamp = data.getStringExtra("timestamp");

                Note newNote = new Note(noteTitle , noteContent , noteTimestamp);
                viewModel.insertNote(newNote);
            }
        }
    }
}
