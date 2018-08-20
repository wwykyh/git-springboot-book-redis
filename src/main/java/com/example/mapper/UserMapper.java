package com.example.mapper;

import com.example.model.Users;

public interface UserMapper {
int insertUser(Users user);
int updateUser(Users user);
Users selectUser(String username,String password);
Users selectOneUser(int userId);
}
