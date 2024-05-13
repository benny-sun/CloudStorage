package com.udacity.jwdnd.c1.cloudstorage.services;

import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.orm.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;
    private final KeyService keyService;

    public UserService(UserMapper userMapper, HashService hashService, KeyService keyService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.keyService = keyService;
    }

    public boolean isUserExist(User user) {
        return getUser(user.getUsername()) != null;
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    public int createUser(User user) {
        String encodedSalt = keyService.generateSecureKey();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        return userMapper.insert(new User(null, user.getUsername(), hashedPassword, encodedSalt, user.getFirstName(), user.getLastName()));
    }
}
