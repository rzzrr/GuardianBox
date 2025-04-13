package com.rzatha.guardianbox.data.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class LoginDbModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "resource_name")
    private String resourceName;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "password")
    private String password;

    public LoginDbModel(int id, String resourceName, String login, String password) {
        this.id = id;
        this.resourceName = resourceName;
        this.login = login;
        this.password = password;
    }

    @Ignore
    public LoginDbModel(String resourceName, String login, String password) {
        this(0, resourceName, login, password);
    }

    public int getId() {
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
