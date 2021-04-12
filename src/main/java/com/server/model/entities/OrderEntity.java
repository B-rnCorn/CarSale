package com.server.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "ordersIdSeq", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersIdSeq")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "status_id")
    private Byte statusId;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "grand_total")
    private Integer grandTotal;

    @ManyToMany
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private Set<CarEntity> cars;
}
