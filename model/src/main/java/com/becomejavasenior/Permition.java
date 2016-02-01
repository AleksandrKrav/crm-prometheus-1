package com.becomejavasenior;

import java.io.Serializable;

public class Permition implements Serializable {

    private boolean canRead;
    private boolean canWrite;
    private boolean canDelete;

    public Permition() {
    }


    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permition permition = (Permition) o;

        if (canRead != permition.canRead) return false;
        if (canWrite != permition.canWrite) return false;
        return canDelete == permition.canDelete;

    }

    @Override
    public int hashCode() {
        int result = (canRead ? 1 : 0);
        result = 31 * result + (canWrite ? 1 : 0);
        result = 31 * result + (canDelete ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permition{" +
                "canRead=" + canRead +
                ", canWrite=" + canWrite +
                ", canDelete=" + canDelete +
                '}';
    }
}
