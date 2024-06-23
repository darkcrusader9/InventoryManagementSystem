package com.ims.controller;

import com.ims.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    Map<String, User> users;

    public UserController() {
        this.users = new HashMap<>();
    }

    public void addUser(User user){
        users.put(user.getUserId(), user);
    }

    public void removeUser(User user){
        users.remove(user.getUserId());
    }

    public User getUserById(String userId){
       return users.get(userId);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }
}
