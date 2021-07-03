package com.lattechiffon.junit.mockito;

import java.util.List;

public interface UserRepository {

    User findByUsername(String username);
    User save(User user);
    List<User> findAll();
}
