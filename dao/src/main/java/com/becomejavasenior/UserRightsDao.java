package com.becomejavasenior;

import java.util.List;

public interface UserRightsDao {
    List<User> getAll();
    Role getRoleById(Integer id);
    void create(User user);
}
