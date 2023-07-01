package com.example.SistemaVentasFacturas.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private double monto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Factura() {
    }

    public Factura(Long id, Date fecha, double monto) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
    }
}
