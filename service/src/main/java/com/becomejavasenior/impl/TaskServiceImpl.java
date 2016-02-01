package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskDao taskDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Task task) throws ServiceException {
        log.info("Add task in service");
        taskDao.persist(task);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Task task) throws ServiceException {
        log.info("Delete tak in service");
        taskDao.delete(task);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Task task) throws ServiceException {
        log.info("Update task in service");
        taskDao.update(task);
    }

    @Override
    public Task getById(Integer id) throws ServiceException {
        log.info("Get task by id  in service");
        return taskDao.getById(id);
    }

    @Override
    public List<Task> getAll() throws ServiceException {
        log.info("Get all tasks  in service");
        return taskDao.getAll();
    }

    @Override
    public Company readCompany(int key) throws ServiceException {
        log.info("Get company by task id  in service");
        return taskDao.readCompany(key);
    }

    @Override
    public Deal readDeal(int key) throws ServiceException {
        log.info("Get deal by task id  in service");
        return taskDao.readDeal(key);
    }

    @Override
    public Contact readContact(int key) throws ServiceException {
        log.info("Get contact by task id  in service");
        return taskDao.readContact(key);
    }

    @Override
    public User readUser(int key) throws ServiceException {
        log.info("Get user by task id  in service");
        return taskDao.readUser(key);
    }

    @Override
    public TaskType readTaskType(int key) throws ServiceException {
        log.info("Get tast type by task id  in service");
        return taskDao.readTaskType(key);
    }

    @Override
    public TaskStatus readTaskStatus(int key) throws ServiceException {
        log.info("Get task status by task id  in service");
        return taskDao.readTaskStatus(key);
    }

    @Override
    public List<TaskType> getAllTaskType() throws ServiceException {
        log.info("Get all task status in service");
        return taskDao.getAllTaskType();
    }
}
