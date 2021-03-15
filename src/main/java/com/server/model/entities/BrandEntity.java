package com.server.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class BrandEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "brandsIdSeq", sequenceName = "brands_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brandsIdSeq")
    private Integer id;

    @Column(name = "name")
    private String brandName;
}
