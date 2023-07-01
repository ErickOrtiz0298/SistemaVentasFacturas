package com.example.SistemaVentasFacturas.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaVentasFacturas.Entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
}
