package com.server.controllers;

import com.server.model.entities.OrderEntity;
import com.server.model.entities.UserEntity;
import com.server.model.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/orders")
    public ResponseEntity<Void> save(@RequestBody OrderEntity order){
        if (order != null) {
            orderService.save(order);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderEntity>> read(){
        List<OrderEntity> orders = orderService.getAll();
        return !(orders == null || orders.isEmpty()) ?
                new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/orders", params = {"id"})
    public ResponseEntity<OrderEntity> read(@RequestParam(name = "id") int id) {
        OrderEntity payment = orderService.get(id);
        return payment != null ?
                new ResponseEntity<>(payment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/orders", params = {"id"})
    public ResponseEntity<Void> delete(@RequestParam(name = "id") int id){
        OrderEntity payment = orderService.get(id);
        orderService.delete(id);
        return !orderService.exist(payment) ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
