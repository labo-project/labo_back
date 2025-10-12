package com.labo.catalog.repository.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "catalog_test")
public class CatalogTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private CatalogArea area;

    @Column(name = "name",nullable = false)
    private String nombre;

    @Column(name = "min_value", nullable = false)
    private BigDecimal valorMin;

    @Column(name = "max_value", nullable = false)
    private BigDecimal valorMax;

    @Column(name = "reference", nullable = false)
    private String referencia;
    
}
