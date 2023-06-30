package com.example.SistemaVentasFacturas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="Factura")
public class Factura {

    @Id
    private int id;
    private Date fecha;

    private double monto;

}
