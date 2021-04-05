package com.server.model.mappers;

import com.server.model.dto.CarDto;
import com.server.model.entities.CarEntity;
import com.server.model.services.ReferenceTableService;
import com.server.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarMapper {
    private final ReferenceTableService refTableService;
    private final UserService userService;

    // From entity to dto
    public List<CarDto> mapAllToDto(List<Object[]> objectsList) {
        List<CarDto> dtoList = null;
        if (objectsList != null) {
            dtoList = new ArrayList<>(objectsList.size());
            for (Object[] objArray : objectsList) {
                dtoList.add(mapToDto(objArray));
            }
        }
        return dtoList;
    }

    public CarDto mapToDto(Object[] objects) {
        CarDto dto = null;
        if (objects != null) {
            dto = new CarDto()
                    .setId((Integer) objects[0])
                    .setBrand((String) objects[1])
                    .setModel((String) objects[2])
                    .setVin((String) objects[3])
                    .setYear(Short.parseShort(objects[4].toString()))
                    .setMileage(Integer.parseInt((objects[5]).toString()))
                    .setEngineCapacity(Float.parseFloat(objects[6].toString()))
                    .setEnginePower(Float.parseFloat(objects[7].toString()))
                    .setBodyType((String) objects[8])
                    .setColor((String) objects[9])
                    .setPrice((Integer) objects[10]);
            if (objects[11] != null)
                    dto.setCustomerId(Integer.parseInt((objects[11]).toString()));
        }
        return dto;
    }

    // From dto to entity
    public CarEntity mapToEntity(CarDto dto) {
        CarEntity entity = null;
        if (dto != null) {
            entity = new CarEntity()
                    .setModelId(refTableService.getModelId(
                            dto.getModel(),
                            dto.getEngineCapacity(),
                            dto.getEnginePower()))
                    .setVin(dto.getVin())
                    .setYear(dto.getYear())
                    .setMileage(dto.getMileage())
                    .setColor(dto.getColor())
                    .setPrice(dto.getPrice())
                    .setCustomerId(dto.getCustomerId());
            if (dto.getId() != null)
                entity.setId(dto.getId());
        }
        return entity;
    }
}
