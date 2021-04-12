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

    public boolean createUser(UserEntity user) {
        UserEntity userFromDB = userRepository.findByEmail(user.getEmail());
        if(userFromDB != null) return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
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
}
