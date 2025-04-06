package com.rzatha.guardianbox.domain.model;

public class Login implements Record {

    private String id;
    private String resourceName;
    private String login;
    private String password;

    public Login(String id, String resourceName, String login, String password) {
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

    public void setId(String id) {
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
