package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.UserDtos.UserDto;
import com.yunus.ecommerce.dto.UserDtos.UserCreateDto;
import com.yunus.ecommerce.dto.UserDtos.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(long userId);
    List<UserDto> getUsers();
    UserDto createUser(UserCreateDto userCreateDto);
    UserDto updateUser(UserUpdateDto userUpdateDto);
    String  deleteUser(long userId);
}
