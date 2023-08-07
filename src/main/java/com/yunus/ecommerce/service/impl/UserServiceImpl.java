package com.yunus.ecommerce.service.impl;

import com.yunus.ecommerce.dto.UserDtos.UserDto;
import com.yunus.ecommerce.dto.UserDtos.UserCreateDto;
import com.yunus.ecommerce.dto.UserDtos.UserUpdateDto;
import com.yunus.ecommerce.entity.User;
import com.yunus.ecommerce.repository.UserRepository;
import com.yunus.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    
    public UserDto getUserById(long userId){
        String errorMessage = "User does not exist";
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(user, UserDto.class) ;
    }


    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }


    public UserDto createUser(UserCreateDto userCreateDto){
        User user = new User();
        user.setUserName(userCreateDto.getUserName());
        user.setUserEmail(userCreateDto.getUserEmail());
        user.setUserPassword(userCreateDto.getUserPassword());
        user.setUserAddress(userCreateDto.getUserAddress());

        try{
            userRepository.save(user);
            return this.modelMapper.map(user,UserDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create user.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public UserDto updateUser(UserUpdateDto userUpdateDto){
        UserDto userDto = getUserById(userUpdateDto.getUserId());
        User user = this.modelMapper.map(userDto, User.class);

        try {
            user.setUserName(userUpdateDto.getUserName());
            user.setUserEmail(userUpdateDto.getUserEmail());
            user.setUserPassword(userUpdateDto.getUserPassword());
            user.setUserAddress(userUpdateDto.getUserAddress());

            userRepository.save(user);

            return this.modelMapper.map(user,UserDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "User failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteUser(long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent() ){
            userRepository.deleteById(userId);
            return "The user deleted.";
        }else {
            return "User not found.";
        }
    }
    
}
