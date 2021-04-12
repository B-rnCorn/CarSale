package com.server.controllers;

import com.server.model.dto.CarDto;
import com.server.model.entities.UserEntity;
import com.server.model.services.OrderService;
import com.server.model.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final UserDetailsServiceImpl userDetailsService;
    private final OrderService orderService;

    @PostMapping(value = "/shoppingcart")
    public ResponseEntity<Void> createShoppingCart(Principal principal, @RequestParam int carId) {
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(principal.getName());

        if (orderService.createOrder(carId, user.getId()))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/shoppingcart/additem")
    public ResponseEntity<Void> addItem(Principal principal, @RequestParam int carId) {
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(principal.getName());

        if(orderService.addItemToOrder(carId, user.getId()))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/shoppingcart")
    public ResponseEntity<List<CarDto>> getAllItems(Principal principal){
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(principal.getName());
        List<CarDto> carDtos = orderService.getAllItemsFromShoppingCart(user.getId());
        return !(carDtos == null || carDtos.isEmpty()) ?
                new ResponseEntity<>(carDtos, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/shoppingcart")
    public ResponseEntity<Void> deleteItem(Principal principal, @RequestParam int carId) {
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(principal.getName());
        return orderService.deleteItemFromShoppingCart(carId, user.getId()) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/shoppingcart/confirmorder")
    public ResponseEntity<Void> confirmOrder(Principal principal) {
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(principal.getName());
        return orderService.confirmOrder(user.getId()) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
