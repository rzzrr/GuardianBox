package com.rzatha.guardianbox.data;

import com.rzatha.guardianbox.data.database.FolderDbModel;
import com.rzatha.guardianbox.data.database.LoginDbModel;
import com.rzatha.guardianbox.data.database.NoteDbModel;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static LoginDbModel mapLoginToLoginDbModel(Login login){
        return new LoginDbModel(
                login.getId(),
                login.getResourceName(),
                login.getLogin(),
                login.getPassword()
                );
    }
    public static Login mapLoginDbModelToLogin(LoginDbModel loginDbModel){
        return new Login(
                loginDbModel.getId(),
                loginDbModel.getResourceName(),
                loginDbModel.getLogin(),
                loginDbModel.getPassword()
        );
    }
    public static NoteDbModel mapNoteToNoteDbModel(Note note){
        return new NoteDbModel(
                note.getId(),
                note.getName(),
                note.getText()
        );
    }
    public static Note mapNoteDbModelToNote(NoteDbModel noteDbModel){
        return new Note(
                noteDbModel.getId(),
                noteDbModel.getName(),
                noteDbModel.getText()
        );
    }
    public static FolderDbModel mapFolderToFolderDbModel(Folder folder){
        return new FolderDbModel(
                folder.getId(),
                folder.getName()
        );
    }
    public static Folder mapFolderDbModelToFolder(FolderDbModel folderDbModel){
        return new Folder(
                folderDbModel.getId(),
                folderDbModel.getName()
        );
    }

    public static List<Login> mapListLoginDbModelToListLogin(List<LoginDbModel> list){
        return list.stream()
                .map(Mapper::mapLoginDbModelToLogin)
                .collect(Collectors.toList());
    }

    public static List<Note> mapListNoteDbModelToListNote(List<NoteDbModel> list){
        return list.stream()
                .map(Mapper::mapNoteDbModelToNote)
                .collect(Collectors.toList());
    }

    public static List<Folder> mapListFolderDbModelToListFolder(List<FolderDbModel> list){
        return list.stream()
                .map(Mapper::mapFolderDbModelToFolder)
                .collect(Collectors.toList());
    }

}
