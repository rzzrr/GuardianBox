package com.rzatha.guardianbox.presentation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.rzatha.guardianbox.R;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Record;

public class RecordAdapter extends ListAdapter<Record, RecyclerView.ViewHolder> {

    private static final int TYPE_FOLDER = 0;
    private static final int TYPE_LOGIN = 1;
    private static final int TYPE_NOTE = 2;

    public RecordAdapter() {
        super(new RecordItemDiffUtil());
    }

    private OnRecordClickListener onRecordClickListener;

    public void setOnRecordClickListener(OnRecordClickListener onRecordClickListener) {
        this.onRecordClickListener = onRecordClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Record record = getItem(position);

        if (record instanceof Folder) return TYPE_FOLDER;

        if (record instanceof Login) return TYPE_LOGIN;

        if (record instanceof Note) return TYPE_NOTE;

        throw new IllegalArgumentException("Invalid record instance");
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Log.d("Adapter", "onCreateViewHolder");

        switch (viewType) {
            case TYPE_FOLDER: {
                View view = inflater.inflate(
                        R.layout.folder_item,
                        parent,
                        false
                );
                return new FolderViewHolder(view);
            }


            case TYPE_LOGIN: {
                View view = inflater.inflate(
                        R.layout.login_item,
                        parent,
                        false
                );
                return new LoginViewHolder(view);
            }
            case TYPE_NOTE: {
                View view = inflater.inflate(
                        R.layout.note_item,
                        parent,
                        false
                );
                return new NoteViewHolder(view);
            }
            default:
                throw new IllegalArgumentException("Invalid view type");

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Record record = getItem(position);
        Log.d("Adapter", "onBindViewHolder");

        if (holder instanceof FolderViewHolder) {
            ((FolderViewHolder) holder).bind((Folder) record);
        }

        if (holder instanceof LoginViewHolder) {
            ((LoginViewHolder) holder).bind((Login) record);
        }

        if (holder instanceof NoteViewHolder) {
            ((NoteViewHolder) holder).bind((Note) record);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecordClickListener != null) {
                    onRecordClickListener.onRecordClick(record);
                }
            }
        });

    }

    static class FolderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        public FolderViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }

        void bind(Folder folder) {
            tvName.setText(folder.getName());
        }

    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        public NoteViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);

        }

        void bind(Note note) {
            tvName.setText(note.getName());
        }

    }

    static class LoginViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvLogin;

        public LoginViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLogin = itemView.findViewById(R.id.tvLogin);
        }

        void bind(Login login) {
            tvName.setText(login.getResourceName());
            tvLogin.setText(login.getLogin());
        }

    }


    public interface OnRecordClickListener {
        void onRecordClick(Record record);
    }
}
