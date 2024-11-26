package com.ead.authuser.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public void deleteById(UUID userId) {
        if(!userRepository.existsById(userId)) {
            //TODO: Tratar quando id não for encontrado
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto save(UserDto userDto) {

        if(userRepository.existsByUsername(userDto.getUsername())) {
            //TODO: Tratar quando usuário já existente
        }

        if(userRepository.existsByEmail(userDto.getEmail())) {
            //TODO: Tratar quando email já existente
        }
        

        var userModel = new UserModel();



        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus((UserStatus.ACTIVE));
        userModel.setUserType((UserType.STUDENT));
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userRepository.save(userModel);

        return userDto;
    }
    
    
}
