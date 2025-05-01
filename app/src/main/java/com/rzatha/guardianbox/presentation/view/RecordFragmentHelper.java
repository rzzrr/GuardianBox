package com.rzatha.guardianbox.presentation.view;

import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.presentation.view.fragment.FolderFragment;
import com.rzatha.guardianbox.presentation.view.fragment.FragmentType;
import com.rzatha.guardianbox.presentation.view.fragment.LoginFragment;
import com.rzatha.guardianbox.presentation.view.fragment.NoteFragment;

public class RecordFragmentHelper {

    public static FragmentType getFragmentType(Record record) {
        if (record instanceof Login) {
            return FragmentType.LOGIN_FRAGMENT;
        } else if (record instanceof Note){
            return FragmentType.NOTE_FRAGMENT;
        } else if (record instanceof Folder){
            return FragmentType.FOLDER_FRAGMENT;
        } else {
            throw new IllegalArgumentException("Unknown instance of record");
        }
    }


    public static Fragment getRightFragment(FragmentType fragmentType, Record record) {
        Fragment result;
        switch (fragmentType) {
            case LOGIN_FRAGMENT:
                if (record != null) {
                    result = LoginFragment.newInstance((Login) record);
                } else {
                    result = LoginFragment.newInstance();
                }
                break;
            case NOTE_FRAGMENT:
                if (record != null) {
                    result = NoteFragment.newInstance((Note) record);
                } else {
                    result = NoteFragment.newInstance();
                }
                break;
            case FOLDER_FRAGMENT:
                if (record != null) {
                    result = FolderFragment.newInstance((Folder) record);
                } else {
                    result = FolderFragment.newInstance();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown fragment type: " + fragmentType.name());
        }
        return result;
    }

    public static  Fragment getRightFragment(FragmentType fragmentType) {
        return getRightFragment(fragmentType, null);
    }

}
