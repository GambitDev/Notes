package com.gambitdev.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CancelNoteCreationDialog extends DialogFragment {

    private OnDialogButtonClick listener;

    void setListener(OnDialogButtonClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.cancel_note_dialog_title)
                .setMessage(R.string.cancel_note_dialog_message)
                .setPositiveButton(R.string.cancel_note_dialog_positive, (dialog, which) ->
                        listener.onPositiveButtonClicked(CancelNoteCreationDialog.this))
                .setNegativeButton(R.string.cancel_note_dialog_negative , (dialog , which) ->
                        listener.onNegativeButtonClicked(CancelNoteCreationDialog.this));
        return builder.create();
    }

    public interface OnDialogButtonClick {
        void onPositiveButtonClicked(CancelNoteCreationDialog dialog);
        void onNegativeButtonClicked(CancelNoteCreationDialog dialog);
    }
}
