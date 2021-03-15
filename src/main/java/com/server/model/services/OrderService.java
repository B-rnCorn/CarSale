package com.server.model.services;

import com.server.model.entities.OrderEntity;
import com.server.model.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void save(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public OrderEntity get(Integer id) {
        return orderRepository.findById(id).isPresent() ?
                orderRepository.findById(id).get() : null;
    }

    public void delete(Integer id){
        orderRepository.deleteById(id);
    }

    public Boolean exist(OrderEntity payment){
        return orderRepository.exists(Example.of(payment));
    }
}
