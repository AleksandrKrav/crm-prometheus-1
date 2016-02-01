package com.becomejavasenior.config;

import com.becomejavasenior.CompanyDao;
import com.becomejavasenior.DealDao;
import com.becomejavasenior.TaskDao;
import com.becomejavasenior.UserDao;
import com.becomejavasenior.daoContact.ContactDao;
import com.becomejavasenior.daoContact.ContactDaoImpl;
import com.becomejavasenior.impl.CompanyDaoImpl;
import com.becomejavasenior.impl.DealDaoImpl;
import com.becomejavasenior.impl.TaskDaoImpl;
import com.becomejavasenior.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * Created by ������������� on 25.01.2016.
 */
@Configuration
@ImportResource("classpath:/Context.xml")
public class ConfigDao {
    @Autowired
    DataSource dataSource;

    @Bean
    public CompanyDao getCompanyDao() {
        return new CompanyDaoImpl(dataSource);
    }

    @Bean
    public DealDao getDealDao() {
        return new DealDaoImpl(dataSource);
    }

    @Bean
    public TaskDao getTaskDao() {
        return new TaskDaoImpl(dataSource);
    }

    @Bean
    public ContactDao getContactDao() {
        return new ContactDaoImpl(dataSource);
    }
}
