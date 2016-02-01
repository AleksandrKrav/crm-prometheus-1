package com.becomejavasenior;

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified extends Serializable {

    /** Возвращает идентификатор объекта */
    public int getId();

    /** Записывает идентификатор объекта */
    public void setId(int id);
}
