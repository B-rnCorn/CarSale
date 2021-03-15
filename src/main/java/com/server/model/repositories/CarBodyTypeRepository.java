package com.server.model.repositories;

import com.server.model.entities.CarBodyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBodyTypeRepository extends JpaRepository<CarBodyTypeEntity, Integer> {
}
