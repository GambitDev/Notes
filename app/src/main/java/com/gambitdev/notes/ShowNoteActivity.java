package com.gambitdev.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ShowNoteActivity extends AppCompatActivity implements DeleteNoteDialog.OnDialogButtonClick ,
        SaveChangesDialog.OnDialogButtonClick {

    private EditText noteTitleEt;
    private EditText noteContentEt;
    private ImageButton enableEditButton;
    private Button editButton;

    //used to identify between back button
    //to edit button call for save changes dialog
    boolean isBackButtonCall;
    boolean isEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        TextView timestampTv = findViewById(R.id.timestamp_tv);
        noteTitleEt = findViewById(R.id.note_title_et);
        noteContentEt = findViewById(R.id.note_content_et);
        editButton = findViewById(R.id.edit_note_button);
        enableEditButton = findViewById(R.id.enable_edit_button);

        isEditMode = getIntent().getBooleanExtra("edit_mode" , false);
        if (isEditMode) {
            editMode();
        }

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String timestamp = "Last modified on " + getIntent().getStringExtra("timestamp");

        timestampTv.setText(timestamp);
        noteTitleEt.setText(title);
        noteContentEt.setText(content);

        enableEditButton.setOnClickListener(v -> {
            isEditMode = true;
            editMode();
        });

        ImageButton deleteNoteButton = findViewById(R.id.delete_note_button);
        deleteNoteButton.setOnClickListener(v -> {
            DeleteNoteDialog dialog = new DeleteNoteDialog();
            dialog.setListener(this);
            dialog.show(getSupportFragmentManager() , "delete_note_dialog");
        });

        editButton.setOnClickListener(v -> confirmSaveChanges());
    }

    @Override
    public void onBackPressed() {
        if (!isEditMode) {
            finish();
        } else {
            isBackButtonCall = true;
            confirmSaveChanges();
        }
    }

    //delete note dialog interface ---
    @Override
    public void onPositiveButtonClicked(DeleteNoteDialog dialog) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("id" , getIntent().getIntExtra("id" , -1));
        setResult(MainActivity.DELETE_NOTE_RESULT_CODE , resultIntent);
        dialog.dismiss();
        finish();
    }

    @Override
    public void onNegativeButtonClicked(DeleteNoteDialog dialog) {
        dialog.dismiss();
    }
    //---

    //save changes to note interface---
    @Override
    public void onPositiveButtonClicked(SaveChangesDialog dialog) {
        String newTitle = noteTitleEt.getText().toString();
        String newContent = noteContentEt.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                Locale.getDefault());
        String newTimestamp = dateFormat.format(Calendar.getInstance().getTime());

        Intent resultIntent = new Intent();
        resultIntent.putExtra("new_title" , newTitle);
        resultIntent.putExtra("new_content" , newContent);
        resultIntent.putExtra("new_timestamp" , newTimestamp);
        resultIntent.putExtra("id" , getIntent().getIntExtra("id" , -1));
        setResult(MainActivity.EDIT_NOTE_RESULT_CODE , resultIntent);
        dialog.dismiss();
        finish();
    }

    @Override
    public void onNegativeButtonClicked(SaveChangesDialog dialog) {
        if (!isBackButtonCall)
            dialog.dismiss();
        else
            finish();
    }
    //---

    void confirmSaveChanges() {
        SaveChangesDialog dialog = new SaveChangesDialog();
        dialog.setListener(this);
        dialog.show(getSupportFragmentManager() , "save_changes_dialog");
    }

    void editMode() {
        enableEditButton.setImageDrawable(getDrawable(R.drawable.ic_edit_pressed));
        editButton.setVisibility(View.VISIBLE);
        noteTitleEt.setEnabled(true);
        noteContentEt.setEnabled(true);
    }
}
