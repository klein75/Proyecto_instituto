package com.instituto.demoj.User.business.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.User.business.services.IUserService;
import com.instituto.demoj.User.domain.dto.generalUserDto;

@Service
public class UserFacade {

    private final IUserService userService;

    @Autowired
    public UserFacade(IUserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<ResponseMessage> createUser(generalUserDto userDto) { ResponseMessage responseMessage = userService.saveUser(userDto); 
        return getResponseEntity(responseMessage); }

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

    private ResponseEntity<ResponseMessage> getResponseEntity(ResponseMessage responseMessage ){ 
        if (responseMessage.isSuccess()) {
             return ResponseEntity.status(HttpStatus.OK).body(responseMessage); } 
             else if (responseMessage.getMessage().contains("ya existe")) {
                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage); } 
                 else { 
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage); } 
                }
}
