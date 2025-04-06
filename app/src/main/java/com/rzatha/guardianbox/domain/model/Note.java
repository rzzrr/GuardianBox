package com.rzatha.guardianbox.domain.model;

public class Note implements Record {

    private String id;
    private String resourceName;
    private String text;

    public Note(String id, String resourceName, String text) {
        this.id = id;
        this.resourceName = resourceName;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getText() {
        return text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setText(String text) {
        this.text = text;
    }
}
