package com.nequi.franquicias.commos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="id_sucursal")
    private Integer subsidiaryId;
    @Column(name="nombre")
    private String name;
    @Column(name="stock")
    private Integer stock;

}
