package com.becomejavasenior;

import java.io.Serializable;
import java.util.Set;

public class Browser implements Serializable{
    private int id;
    private String name;

    public Browser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Browser browser = (Browser) o;

        if (id != browser.id) return false;
        return !(name != null ? !name.equals(browser.name) : browser.name != null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Browser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
