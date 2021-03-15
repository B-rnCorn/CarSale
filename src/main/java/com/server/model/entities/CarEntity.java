package com.server.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "carsIdSeq", sequenceName = "cars_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carsIdSeq")
    private Integer id;

    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "vin")
    private String vin;

    @Column(name = "year")
    private Short year;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Integer price;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "customer_id")
    private Integer customerId;
}
