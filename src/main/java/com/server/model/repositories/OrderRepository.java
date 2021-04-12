package com.server.model.repositories;

import com.server.model.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByCustomerIdAndAndStatusId(int customerId, byte statusId);
}
