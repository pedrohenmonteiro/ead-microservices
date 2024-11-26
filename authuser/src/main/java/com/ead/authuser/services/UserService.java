package com.ead.authuser.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;

public interface UserService {
List<UserModel> findAll();

UserModel findById(UUID userId);

void deleteById(UUID userId);

UserDto save(UserDto userDto);



}
