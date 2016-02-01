package com.becomejavasenior;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {

    private int id;
    private String name;
    private Permition permition;

    public Role() {

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

    public Permition getPermition() {
        return permition;
    }

    public void setPermition(Permition permition) {
        this.permition = permition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        return !(permition != null ? !permition.equals(role.permition) : role.permition != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (permition != null ? permition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permition=" + permition +
                '}';
    }
}
