package com.server.model.repositories;

import com.server.model.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {

    @Query(value ="select c.id, m.brand_name, m.model_name, c.vin, " +
                    "c.year, c.mileage, m.engine_capacity, m.engine_power, " +
                    "m.type, c.color, c.price, c.customer_id " +
                    "from cars as c " +
                        "join (select models.id, brands.name as brand_name, models.name as model_name, " +
                                "models.engine_capacity, models.engine_power, cbt.type " +
                                "from models " +
                                    "join brands on models.brand_id = brands.id " +
                                    "join car_body_types as cbt on models.body_type_id = cbt.id) as m " +
                        "on c.model_id = m.id " +
                    "where c.customer_id is null " +
                    "order by id",
            nativeQuery = true)
    List<Object[]> joinCar();
}
