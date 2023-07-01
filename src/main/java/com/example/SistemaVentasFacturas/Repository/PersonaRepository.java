package com.example.SistemaVentasFacturas.Repository;

import java.util.List;
import java.util.Optional;

import com.example.SistemaVentasFacturas.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaVentasFacturas.Entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Persona findByIdentificacion(String identificacion);

    void deleteByIdentificacion(String identificacion);
}
