package com.becomejavasenior;

import java.util.List;

/**
 * Created by Artem Kozlenko <ajet5911@gmail.com> on 14.01.2016.
 */
public interface UserService extends GenericService<User, UserDao> {
    Role readRole(int key) throws ServiceException;
    User getUserByEmail(String userName) throws ServiceException;

}
