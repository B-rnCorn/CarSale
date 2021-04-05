package com.server.model.services;

import com.server.model.entities.CarBodyTypeEntity;
import com.server.model.entities.ModelEntity;
import com.server.model.repositories.CarBodyTypeRepository;
import com.server.model.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceTableService {
    private final CarBodyTypeRepository cbtRepository;
    private final ModelRepository modelRepository;

    public void saveBodyType(CarBodyTypeEntity cbtEntity) {
        cbtRepository.save(cbtEntity);
    }

    public void saveModel(ModelEntity modelEntity) {
        modelRepository.save(modelEntity);
    }


    public List<CarBodyTypeEntity> getAllBodyTypes() {
        return cbtRepository.findAll();
    }

    public List<ModelEntity> getAllModels() {
        return modelRepository.findAll();
    }


    public CarBodyTypeEntity getBodyType(Integer id) {
        return cbtRepository.findById(id).isPresent() ?
                cbtRepository.findById(id).get() : null;
    }

    public ModelEntity getModel(Integer id) {
        return modelRepository.findById(id).isPresent() ?
                modelRepository.findById(id).get() : null;
    }


    public void deleteBodyType(Integer id) {
        cbtRepository.deleteById(id);
    }

    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }


    public Boolean existBodyType(CarBodyTypeEntity carBodyTypeEntity) {
        return cbtRepository.exists(Example.of(carBodyTypeEntity));
    }

    public Boolean existModel(ModelEntity modelEntity) {
        return modelRepository.exists(Example.of(modelEntity));
    }


    public Byte getBodyTypeId(String bodyTypeName) {
        for (CarBodyTypeEntity entity : getAllBodyTypes()) {
            if (entity.getTypeName().equals(bodyTypeName))
                return entity.getId();
        }
        return null;
    }

    public Integer getModelId(String modelName, Float engineCapacity, Float enginePower) {
        for (ModelEntity entity : getAllModels()) {
            if (entity.getModelName().equals(modelName)
                    && entity.getEngineCapacity().equals(engineCapacity)
                    && entity.getEnginePower().equals(enginePower))
                return entity.getId();
        }
        return null;
    }
}
