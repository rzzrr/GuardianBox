package com.rzatha.guardianbox.data.database;

import androidx.room.Database;

@Database(
        entities = {LoginDbModel.class, FolderDbModel.class, NoteDbModel.class},
        version = 1
)
abstract class AppDatabase {

}
