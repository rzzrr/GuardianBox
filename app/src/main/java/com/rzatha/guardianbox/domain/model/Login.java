package com.rzatha.guardianbox.domain.model;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Login extends Record implements Serializable {

    private int id;
    private String resourceName;
    private String login;
    private String password;

    public Login(int id, String resourceName, String login, String password) {
        this.id = id;
        this.resourceName = resourceName;
        this.login = login;
        this.password = password;
    }

    public Login(String resourceName, String login, String password){
        this(0, resourceName, login, password);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Login login1 = (Login) o;
        return Objects.equals(id, login1.id) && Objects.equals(resourceName, login1.resourceName) && Objects.equals(login, login1.login) && Objects.equals(password, login1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourceName, login, password);
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

    public void setId(int id) {
        this.id = id;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
