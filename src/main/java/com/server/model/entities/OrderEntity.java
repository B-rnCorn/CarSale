package com.server.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
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
    private Date date;

    @Column(name = "grand_total")
    private Integer grandTotal;
}
