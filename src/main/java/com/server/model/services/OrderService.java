package com.server.model.services;

import com.server.model.dto.CarDto;
import com.server.model.entities.CarEntity;
import com.server.model.entities.OrderEntity;
import com.server.model.mappers.CarMapper;
import com.server.model.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CarService carService;
    private final CarMapper carMapper;

    public void save(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public boolean createOrder(int carId, int userId){
        OrderEntity newOrder = null;
        CarEntity car = carService.get(carId);
        if(car != null && car.getCustomerId() == null) {

            newOrder = new OrderEntity();
            newOrder.setCustomerId(userId)
                    .setStatusId((byte) 4) // Статус "Корзина"
                    .setDate(new Timestamp(System.currentTimeMillis()))
                    .setGrandTotal(car.getPrice())
                    .setCars(Collections.singleton(car));
            orderRepository.save(newOrder);

            car.setCustomerId(userId);
            carService.save(car);
            return true;
        }
        return false;
    }

    public boolean addItemToOrder(int carId, int userId) {
        CarEntity car = carService.get(carId);

        if(car != null && car.getCustomerId() == null) {
            OrderEntity order = orderRepository.findByCustomerIdAndAndStatusId(userId, (byte) 4); //statusId = 4 - "Корзина"

            if(order == null || order.getCars().contains(car)) return false;

            order.getCars().add(car);
            orderRepository.save(order);

            car.setCustomerId(userId);
            carService.save(car);
            return true;
        }
        return false;
    }

    public List<CarDto> getAllItemsFromShoppingCart(int userId) {
        OrderEntity order = orderRepository.findByCustomerIdAndAndStatusId(userId, (byte) 4); //statusId = 4 - "Корзина"
        List<CarDto> carDtos = null;

        if(order != null) {
            carDtos = new ArrayList<>();
            for (CarEntity car : order.getCars())
                carDtos.add(carMapper.mapToDto(carService.joinCar(car.getId())));
        }
        return carDtos;
    }

    public boolean deleteItemFromShoppingCart(int carId, int userId) {
        CarEntity car = carService.get(carId);
        OrderEntity order = orderRepository.findByCustomerIdAndAndStatusId(userId, (byte) 4); //statusId = 4 - "Корзина"

        if (order != null && car != null) {
           order.getCars().remove(carService.get(carId));
           orderRepository.save(order);

           car.setCustomerId(null);
           carService.save(car);
           return true;
        }
        return false;
    }

    public boolean confirmOrder(int userId) {
        OrderEntity order = orderRepository.findByCustomerIdAndAndStatusId(userId, (byte) 4); //statusId = 4 - "Корзина"
        if (order != null){
            order.setStatusId((byte) 3);
            orderRepository.save(order);
            return true;
        }
        return false;
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
