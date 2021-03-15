package com.server.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "models")
public class ModelEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "modelsIdSeq", sequenceName = "models_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modelsIdSeq")
    private Integer id;

    @Column(name = "name")
    private String modelName;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "engine_capacity")
    private Float engineCapacity;

    @Column(name = "engine_power")
    private Float enginePower;

    @Column(name = "body_type_id")
    private Byte carBodyType;
}
