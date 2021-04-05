package com.server.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private Float engineCapacity;
    private Float enginePower;
    private String vin;
    private Short year;
    private Integer mileage;
    private String bodyType;
    private String color;
    private Integer price;
    private Integer customerId;
}
