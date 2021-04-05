package com.server.controllers;

import com.server.model.dto.CarDto;
import com.server.model.entities.CarEntity;
import com.server.model.mappers.CarMapper;
import com.server.model.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;
    @RequestMapping(value = "/cars", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Void> save(@RequestBody CarDto carDto) {
        CarEntity carEntity = carMapper.mapToEntity(carDto);
        if(carEntity != null){
            carService.save(carEntity);
            if (carEntity.equals(carService.get(carEntity.getId())))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<List<CarDto>> read(){
        List<CarDto> carDtos = carMapper.mapAllToDto(carService.joinCars());

        return !(carDtos == null || carDtos.isEmpty()) ?
                new ResponseEntity<>(carDtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cars", params = {"id"})
    public ResponseEntity<CarDto> read(@RequestParam(name = "id") int id){
        CarDto carDto = carMapper.mapToDto(carService.joinCar(id));

        return carDto != null ? new ResponseEntity<>(carDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/cars", params = {"id"})
    public ResponseEntity<Void> delete(@RequestParam(name = "id") int id) {
        CarEntity carEntity = carService.get(id);
        if (carService.exist(carEntity)) {
            carService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
