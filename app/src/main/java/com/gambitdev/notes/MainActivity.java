package com.gambitdev.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnItemClickListener ,
        DeleteNoteDialog.OnDialogButtonClick {

    private NoteViewModel viewModel;
    private static final int ADD_NOTE_REQUEST_CODE = 1;
    private static final int SHOW_NOTE_REQUEST_CODE = 2;
    static final int EDIT_NOTE_RESULT_CODE = 3;
    static final int DELETE_NOTE_RESULT_CODE = 4;

    //used for deleting notes
    private int noteToDeleteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv);
        NotesAdapter adapter = new NotesAdapter();
        adapter.setListener(this);
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
        } else if (requestCode == SHOW_NOTE_REQUEST_CODE && resultCode == DELETE_NOTE_RESULT_CODE) {
            if (data != null) {
                noteToDeleteID = data.getIntExtra("id", -1);
                viewModel.deleteNote(noteToDeleteID);
            }
        } else if (requestCode == SHOW_NOTE_REQUEST_CODE && resultCode == EDIT_NOTE_RESULT_CODE) {
            if (data != null) {
                String newTitle = data.getStringExtra("new_title");
                String newContent = data.getStringExtra("new_content");
                String newTimestamp = data.getStringExtra("new_timestamp");
                int idForUpdate = data.getIntExtra("id" , -1);
                viewModel.updateNote(idForUpdate , newTitle , newContent , newTimestamp);
            }
        }
    }

    @Override
    public void onReadMoreClick(Note note) {
        Intent intent = new Intent(MainActivity.this , ShowNoteActivity.class);
        intent.putExtra("id" , note.getId());
        intent.putExtra("title" , note.getTitle());
        intent.putExtra("content" , note.getContent());
        intent.putExtra("timestamp" , note.getTimestamp());
        intent.putExtra("edit_mode" , false);

        startActivityForResult(intent , SHOW_NOTE_REQUEST_CODE);
    }

    @Override
    public void onEditButtonClick(Note note) {
        Intent intent = new Intent(MainActivity.this , ShowNoteActivity.class);
        intent.putExtra("id" , note.getId());
        intent.putExtra("title" , note.getTitle());
        intent.putExtra("content" , note.getContent());
        intent.putExtra("timestamp" , note.getTimestamp());
        intent.putExtra("edit_mode" , true);

        startActivityForResult(intent , SHOW_NOTE_REQUEST_CODE);
    }

    @Override
    public void onDeleteButtonClick(Note note) {
        noteToDeleteID = note.getId();
        DeleteNoteDialog dialog = new DeleteNoteDialog();
        dialog.setListener(this);
        dialog.show(getSupportFragmentManager() , "delete_note");
    }

    @Override
    public void onPositiveButtonClicked(DeleteNoteDialog dialog) {
        viewModel.deleteNote(noteToDeleteID);
        dialog.dismiss();
    }

    @Override
    public void onNegativeButtonClicked(DeleteNoteDialog dialog) {
        dialog.dismiss();
    }
}
