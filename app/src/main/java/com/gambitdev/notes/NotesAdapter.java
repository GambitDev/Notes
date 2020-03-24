package com.gambitdev.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteList;

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_list_item ,
                parent , false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note current = noteList.get(position);
        String title = current.getTitle();
        holder.noteTitleTv.setText(title);
    }

    @Override
    public int getItemCount() {
        if (noteList != null)
            return noteList.size();
        else
            return 0;
    }

    void setNoteList(List<Note> notes) {
        noteList = notes;
        notifyDataSetChanged();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitleTv;

        NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitleTv = itemView.findViewById(R.id.note_title_tv);
        }
    }
}
