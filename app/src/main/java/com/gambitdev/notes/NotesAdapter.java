package com.gambitdev.notes;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
