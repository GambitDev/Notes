package com.gambitdev.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SaveChangesDialog extends DialogFragment {

    private OnDialogButtonClick listener;

    void setListener(OnDialogButtonClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.save_changes_dialog_title)
                .setMessage(R.string.save_changes_dialog_message)
                .setPositiveButton(R.string.save_changes_dialog_positive, (dialog, which) ->
                        listener.onPositiveButtonClicked(SaveChangesDialog.this))
                .setNegativeButton(R.string.save_changes_dialog_negative , (dialog, which) ->
                        listener.onNegativeButtonClicked(SaveChangesDialog.this));
        return builder.create();
    }

    interface OnDialogButtonClick {
        void onPositiveButtonClicked(SaveChangesDialog dialog);
        void onNegativeButtonClicked(SaveChangesDialog dialog);
    }
}
