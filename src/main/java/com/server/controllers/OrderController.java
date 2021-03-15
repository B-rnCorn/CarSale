package com.server.controllers;

import com.server.model.entities.OrderEntity;
import com.server.model.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final static String BASE_CATALOG = "/dealership/payments";

    @RequestMapping(value = BASE_CATALOG, method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> save(@RequestBody OrderEntity payment){
        if (payment != null) {
            orderService.save(payment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = BASE_CATALOG)
    public ResponseEntity<List<OrderEntity>> read(){
        List<OrderEntity> payments = orderService.getAll();
        return !(payments == null || payments.isEmpty()) ?
                new ResponseEntity<>(payments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = BASE_CATALOG, params = {"id"})
    public ResponseEntity<OrderEntity> read(@RequestParam(name = "id") int id) {
        OrderEntity payment = orderService.get(id);
        return payment != null ?
                new ResponseEntity<>(payment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = BASE_CATALOG, params = {"id"})
    public ResponseEntity<?> delete(@RequestParam(name = "id") int id){
        OrderEntity payment = orderService.get(id);
        orderService.delete(id);
        return !orderService.exist(payment) ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
