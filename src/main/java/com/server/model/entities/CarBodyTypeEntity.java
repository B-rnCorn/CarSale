package com.server.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "car_body_types")
public class CarBodyTypeEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "brandsIdSeq", sequenceName = "brands_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brandsIdSeq")
    private Byte id;

    @Column(name = "type")
    private String typeName;
}
