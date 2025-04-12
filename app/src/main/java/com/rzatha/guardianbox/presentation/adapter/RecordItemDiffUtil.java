package com.rzatha.guardianbox.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Record;

public class RecordItemDiffUtil extends DiffUtil.ItemCallback<Record> {

    @Override
    public boolean areItemsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
        if (oldItem instanceof Login && newItem instanceof Login) {
            return ((Login) oldItem).getId() == (((Login) newItem).getId());
        } else if (oldItem instanceof Note && newItem instanceof Note) {
            return ((Note) oldItem).getId() == (((Note) newItem).getId());
        } else if (oldItem instanceof Folder && newItem instanceof Folder) {
            return ((Folder) oldItem).getId() == (((Folder) newItem).getId());
        } else {
            throw new IllegalStateException("Unknown instance of item");
        }
    }

    @Override
    public boolean areContentsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
        return oldItem.equals(newItem);
    }
}
