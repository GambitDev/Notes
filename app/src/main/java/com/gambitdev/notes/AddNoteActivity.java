package com.gambitdev.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity implements CancelDialog.OnDialogButtonClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText noteTitleEt = findViewById(R.id.note_title_et);
        EditText noteContentEt = findViewById(R.id.note_content_et);

        Button createNoteBtn = findViewById(R.id.create_note_btn);
        createNoteBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(noteTitleEt.getText())) {
                Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
                return;
            }
            String noteTitle = noteTitleEt.getText().toString();
            String noteContent = noteContentEt.getText().toString();
            String timestamp = getTimestamp();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title" , noteTitle);
            resultIntent.putExtra("content" , noteContent);
            resultIntent.putExtra("timestamp" , timestamp);
            setResult(RESULT_OK , resultIntent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        CancelDialog dialog = new CancelDialog();
        dialog.setListener(this);
        dialog.show(getSupportFragmentManager() , "cancel_dialog");
    }

    String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm",
                Locale.getDefault());
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    @Override
    public void onPositiveButtonClicked(CancelDialog dialog) {
        dialog.dismiss();
        finish();
    }

    @Override
    public void onNegativeButtonClicked(CancelDialog dialog) {
        dialog.dismiss();
    }
}
