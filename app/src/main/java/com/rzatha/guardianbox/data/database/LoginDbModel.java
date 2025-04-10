package com.rzatha.guardianbox.data.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class LoginDbModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "resource_name")
    private String resourceName;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "password")
    private String password;

    public LoginDbModel(String id, String resourceName, String login, String password) {
        this.id = id;
        this.resourceName = resourceName;
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
