package com.tfive.blog.service;

import com.tfive.blog.po.User;

public interface UserService {

    User checkUser(String username,String password);

}
