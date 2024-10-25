package com.instituto.demoj.User.business.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instituto.demoj.User.business.services.IUserService;
import com.instituto.demoj.User.domain.dto.generalUserDto;
import com.instituto.demoj.Utilities.ResponseMessage.ResponseMessage;

@Service
public class UserFacade {

    private final IUserService userService;

    @Autowired
    public UserFacade(IUserService userService) {
        this.userService = userService;
    }

     public ResponseMessage createUser(generalUserDto userDto) {
        return userService.saveUser(userDto);
    }

    public ResponseMessage updateUser(Long id, generalUserDto generalUserDto) {
        return userService.updateUser(id, generalUserDto);
    }

    public ResponseMessage deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    public ResponseMessage getUserById(Long id) {
        return userService.getUserById(id);
    }

    public ResponseMessage getAllUsers() {
        return userService.getAllUsers();
    }

    public ResponseMessage getAlumnos() {
        return userService.getAlumnos();
    }
}
