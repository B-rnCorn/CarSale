package com.server.model.services;

import com.server.model.entities.Role;
import com.server.model.entities.UserEntity;
import com.server.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public void createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setActive(true);
        userEntity.setRoles(Collections.singleton(Role.USER));
        userRepository.save(userEntity);
    }

    public UserEntity getUser(Integer id) {
        return userRepository.findById(id).isPresent() ?
                userRepository.findById(id).get() : null;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean existUser(UserEntity userEntity) {
        return userRepository.exists(Example.of(userEntity));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public Integer getUserId(String fullName) {
        String[] fullNameAr = fullName.trim().split(" ");
        for (UserEntity entity : getAllUsers()) {
            if (entity.getFirstName().equals(fullNameAr[0])
                    && entity.getLastName().equals(fullNameAr[1]))
                return entity.getId();
        }
        return null;

        //return userRepository.findByFirstNameAndLastName(fullNameAr[0], fullNameAr[1]);
    }
}
