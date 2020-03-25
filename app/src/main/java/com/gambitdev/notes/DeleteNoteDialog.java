package com.gambitdev.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteNoteDialog extends DialogFragment {

    private OnDialogButtonClick listener;

    void setListener(OnDialogButtonClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.delete_note_dialog_title)
                .setMessage(R.string.delete_note_dialog_message)
                .setPositiveButton(R.string.delete_note_dialog_positive, (dialog, which) ->
                        listener.onPositiveButtonClicked(DeleteNoteDialog.this))
                .setNegativeButton(R.string.delete_note_dialog_negative , (dialog, which) ->
                        listener.onNegativeButtonClicked(DeleteNoteDialog.this));
        return builder.create();
    }

    public interface OnDialogButtonClick {
        void onPositiveButtonClicked(DeleteNoteDialog dialog);
        void onNegativeButtonClicked(DeleteNoteDialog dialog);
    }
}
