package com.becomejavasenior;

import java.util.List;

public interface UserRightService {
    Role getRoleById(Integer id);
    List<User> getAll();
    void create(User user);
}
