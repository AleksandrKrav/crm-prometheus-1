package com.becomejavasenior;

import java.util.List;

/**
 * Created by Sergey on 21.12.2015.
 */
public interface TaskDao extends GenericDao<Task> {

    public Company readCompany(int key) throws PersistException;
    public Deal readDeal(int key) throws PersistException;
    public Contact readContact(int key) throws PersistException;
    public User readUser(int key) throws PersistException;
    public TaskType readTaskType(int key) throws PersistException;
    public TaskStatus readTaskStatus(int key) throws PersistException;

    public List<TaskType> getAllTaskType();

}
