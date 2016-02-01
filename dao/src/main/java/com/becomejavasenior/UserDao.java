package com.becomejavasenior;

public interface UserDao extends GenericDao<com.becomejavasenior.User> {
    public com.becomejavasenior.Role readRole(int key) throws PersistException;
    User getUserByEmail(String userName) throws  PersistException;
}
