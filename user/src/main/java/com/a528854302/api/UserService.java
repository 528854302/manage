package com.a528854302.api;

import com.a528854302.entity.Admin;

public interface UserService {
    Admin select(String username,String password);
}
