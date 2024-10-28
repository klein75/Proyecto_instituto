package com.instituto.demoj.User.domain.dto;

import java.util.Set;

import com.instituto.demoj.Roles.domain.Entity.RoleEntity;

import com.instituto.demoj.Utilities.Enum.StateEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class teacherUserDto {

    private String firstName;
    private String secName;
    private String surName;
    private String secSurname;
    private String document;
    private String email;
    private String phone;
    private String nickname;
    private String image;
    private StateEnum state;
    private Set<RoleEntity> roles;

}
