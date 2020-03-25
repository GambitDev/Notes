package com.gambitdev.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteList;
    private OnItemClickListener listener;

    void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

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
        holder.noteTitle.setText(current.getTitle());

        String content = current.getContent();
        if (content.length() > 100) {
            String shortContent = content.substring(0 , 100) + "...";
            holder.noteContent.setText(shortContent);
        } else {
            holder.noteContent.setText(content);
        }

        holder.readMoreButton.setOnClickListener(v ->
                listener.onReadMoreClick(current));

        holder.deleteButton.setOnClickListener(v ->
                listener.onDeleteButtonClick(current));

        holder.editButton.setOnClickListener(v ->
                listener.onEditButtonClick(current));
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

        Button readMoreButton;
        ImageButton editButton , deleteButton;
        TextView noteTitle , noteContent;

        NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            readMoreButton = itemView.findViewById(R.id.read_more_button);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
            noteTitle = itemView.findViewById(R.id.card_title);
            noteContent = itemView.findViewById(R.id.card_content);
        }
    }

    interface OnItemClickListener {
        void onReadMoreClick(Note note);
        void onEditButtonClick(Note note);
        void onDeleteButtonClick(Note note);
    }
}
