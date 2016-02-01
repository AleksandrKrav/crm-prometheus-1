package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


//    @Transactional()
    @Override
    public void add(User user) throws ServiceException {
        log.info("Add user in service");
        userDao.persist(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        log.info("Delete user in service");
        userDao.delete(user);
    }

    @Override
    public void update(User user) throws ServiceException {
        log.info("Update user in service");
        userDao.update(user);
    }

    @Override
    public User getById(Integer id) throws ServiceException {
        log.info("Get user by id  in service");
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() throws ServiceException {
        log.info("Get all users  in service");
        return userDao.getAll();
    }

    @Override
    public Role readRole(int key) throws ServiceException {
        log.info("Get role by id in service");
        return userDao.readRole(key);
    }

    @Override
    public User getUserByEmail(String userEmail) throws ServiceException {
        return userDao.getUserByEmail(userEmail);
    }

}
