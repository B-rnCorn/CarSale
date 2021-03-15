package com.server.model.services;

import com.server.model.entities.CarBodyTypeEntity;
import com.server.model.entities.ModelEntity;
import com.server.model.entities.UserEntity;
import com.server.model.repositories.CarBodyTypeRepository;
import com.server.model.repositories.ModelRepository;
import com.server.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceTableService {
    private final CarBodyTypeRepository cbtRepository;
    private final ModelRepository modelRepository;
    public final UserRepository userRepository;

    public void saveBodyType(CarBodyTypeEntity cbtEntity) {
        cbtRepository.save(cbtEntity);
    }

    public void saveModel(ModelEntity modelEntity) {
        modelRepository.save(modelEntity);
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }


    public List<CarBodyTypeEntity> getAllBodyTypes() {
        return cbtRepository.findAll();
    }

    public List<ModelEntity> getAllModels() {
        return modelRepository.findAll();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    public CarBodyTypeEntity getBodyType(Integer id) {
        return cbtRepository.findById(id).isPresent() ?
                cbtRepository.findById(id).get() : null;
    }

    public ModelEntity getModel(Integer id) {
        return modelRepository.findById(id).isPresent() ?
                modelRepository.findById(id).get() : null;
    }

    public UserEntity getUser(Integer id) {
        return userRepository.findById(id).isPresent() ?
                userRepository.findById(id).get() : null;
    }


    public void deleteBodyType(Integer id) {
        cbtRepository.deleteById(id);
    }

    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    public Boolean existBodyType(CarBodyTypeEntity carBodyTypeEntity) {
        return cbtRepository.exists(Example.of(carBodyTypeEntity));
    }

    public Boolean existModel(ModelEntity modelEntity) {
        return modelRepository.exists(Example.of(modelEntity));
    }

    public Boolean existUser(UserEntity userEntity) {
        return userRepository.exists(Example.of(userEntity));
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

    public Integer getUserId(String userName) {
        String[] fullNameAr = userName.trim().split(" ");
        for (UserEntity entity : getAllUsers()) {
            if (entity.getFirstName().equals(fullNameAr[0])
                    && entity.getLastName().equals(fullNameAr[1])
                    && entity.getIsManager())
                return entity.getId();
        }
        return null;
    }
}
