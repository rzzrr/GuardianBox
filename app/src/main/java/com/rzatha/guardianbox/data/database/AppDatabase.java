package com.rzatha.guardianbox.data.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

@Database(
        entities = {LoginDbModel.class, FolderDbModel.class, NoteDbModel.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecordDao recordDao();

    private static AppDatabase INSTANCE = null;
    private static final Object LOCK = new Object();
    private static final String DB_NAME = "records.db";

    public static AppDatabase getInstance(Application application){
        if (INSTANCE!=null) return INSTANCE;
        synchronized (LOCK){
            if (INSTANCE!=null) return INSTANCE;

            AppDatabase dbInstance =  Room.databaseBuilder(
                    application,
                    AppDatabase.class,
                    DB_NAME
            )
                    .build();

            INSTANCE = dbInstance;
            return dbInstance;
        }
    }
}
