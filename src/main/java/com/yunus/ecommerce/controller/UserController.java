package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.UserDtos.UserCreateDto;
import com.yunus.ecommerce.dto.UserDtos.UserDto;
import com.yunus.ecommerce.dto.UserDtos.UserUpdateDto;
import com.yunus.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @PutMapping("/update")
    public UserDto updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(userUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteUser(@RequestParam long userId) {
        return userService.deleteUser(userId);
    }

}
