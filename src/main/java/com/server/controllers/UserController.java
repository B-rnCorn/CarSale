package com.server.controllers;

import com.server.model.entities.UserEntity;
import com.server.model.services.ReferenceTableService;
import com.server.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final static String BASE_CATALOG = "/dealership/users";

    @RequestMapping(value = BASE_CATALOG, method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> save(@RequestBody UserEntity userEntity) {
        if (userEntity != null) {
            userService.saveUser(userEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = BASE_CATALOG)
    public ResponseEntity<List<UserEntity>> read() {
        List<UserEntity> users = userService.getAllUsers();
        return !(users == null || users.isEmpty()) ?
                new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = BASE_CATALOG, params = {"id"})
    public ResponseEntity<UserEntity> read(@RequestParam(name = "id") int id) {
        UserEntity user = userService.getUser(id);
        return user != null ?
                new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = BASE_CATALOG, params = {"id"})
    public ResponseEntity<?> delete(@RequestParam(name = "id") int id) {
        UserEntity user = userService.getUser(id);
        userService.deleteUser(id);
        return !userService.existUser(user) ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
