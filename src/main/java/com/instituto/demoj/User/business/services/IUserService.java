package com.instituto.demoj.User.business.services;



import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.User.domain.dto.generalUserDto;

public interface IUserService {

    ResponseMessage saveUser(generalUserDto generalUserDto);

    ResponseMessage updateUser(Long id, generalUserDto generalUserDto);

    ResponseMessage deleteUser(Long id);

    ResponseMessage getUserById(Long id);

    ResponseMessage getAllUsers();

    ResponseMessage getAlumnos();
}

