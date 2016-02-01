package com.becomejavasenior;

import java.util.List;

public interface TaskService extends GenericService<Task, TaskDao> {
    Company readCompany(int key) throws ServiceException;
    Deal readDeal(int key) throws ServiceException;
    Contact readContact(int key) throws ServiceException;
    User readUser(int key) throws ServiceException;
    TaskType readTaskType(int key) throws ServiceException;
    TaskStatus readTaskStatus(int key) throws ServiceException;
    List<TaskType> getAllTaskType() throws ServiceException;
}
