package com.tfive.blog.dao;

import com.tfive.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;


//这里不同的是继承了JpaRepository User代表操作对象(也就是表)
//JpaRepository 中已经定义了增删改查(我们需要遵循命名规则)

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsernameAndPassword(String username,String password);

}
