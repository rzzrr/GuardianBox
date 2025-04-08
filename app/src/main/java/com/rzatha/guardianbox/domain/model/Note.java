package com.rzatha.guardianbox.domain.model;

import java.util.Objects;

public class Note extends Record {

    private String id;
    private String name;
    private String text;

    public Note(String id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(name, note.name) && Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text);
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }
}
