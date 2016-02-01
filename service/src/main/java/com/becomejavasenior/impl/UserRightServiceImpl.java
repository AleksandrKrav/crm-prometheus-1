package com.becomejavasenior.impl;

import com.becomejavasenior.JDBCTemplate.UserRightsJDBCTemplate;
import com.becomejavasenior.Role;
import com.becomejavasenior.User;
import com.becomejavasenior.UserRightService;
import com.becomejavasenior.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserRightServiceImpl implements UserRightService {
    private static final Logger log = Logger.getLogger(UserRightServiceImpl.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");
    private UserRightsJDBCTemplate userRightsJDBCTemplate = (UserRightsJDBCTemplate) context.getBean("userRightsJDBCTemplate");

    @Override
    public Role getRoleById(Integer id) {
        log.warn("Get role by id in service");
        return userRightsJDBCTemplate.getRoleById(id);
    }

    @Override
    public List<User> getAll() {
        log.warn("Get list users in service");
        return userRightsJDBCTemplate.getAll();
    }

    @Override
    public void create(User user) {
        log.warn("Create user in service");
        userRightsJDBCTemplate.create(user);
    }
}
