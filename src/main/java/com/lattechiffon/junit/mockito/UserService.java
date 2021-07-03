package com.lattechiffon.junit.mockito;

import java.util.List;

public class UserService {
    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (user.getUsername().isEmpty()) {
            return null;
        }

        return userRepository.save(user);
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
