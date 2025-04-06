package com.rzatha.guardianbox.domain.model;

public class Package implements Record {

    private String id;
    private String packageName;

    public Package(String id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }

    public String getId() {
        return id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
