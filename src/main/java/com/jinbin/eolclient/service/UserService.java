package com.jinbin.eolclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinbin.eolclient.pojo.User;

public interface UserService extends IService<User> {

    User getUserByUsername(String username) ;

    User getUserByUsernameAndPassword(String username, String password);
}
