package com.server.model.services;

import com.server.model.entities.CarEntity;
import com.server.model.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public void save(CarEntity carEntity) {
        carRepository.save(carEntity);
    }

    public List<CarEntity> getAll() {
        return carRepository.findAll();
    }

    public CarEntity get(Integer id) {
        return carRepository.findById(id).isPresent() ?
                carRepository.findById(id).get() : null;
    }

    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    public Boolean exist(CarEntity carEntity) {
        return carEntity != null && carRepository.exists(Example.of(carEntity));
    }

    public Object[] joinCar(Integer id) {
        if (carRepository.existsById(id)) {
            List<Object[]> objects = carRepository.joinCar();
            for (Object[] objArray : objects) {
                if (id.equals(objArray[0]))
                    return objArray;
            }
        }
        return null;
    }

    public List<Object[]> joinCars() {
        return carRepository.joinCar();
    }
}
