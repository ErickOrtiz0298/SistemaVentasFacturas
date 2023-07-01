package com.example.SistemaVentasFacturas.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SistemaVentasFacturas.Entity.Persona;
import com.example.SistemaVentasFacturas.Service.DirectorioRestService;
import com.example.SistemaVentasFacturas.Repository.PersonaRepository;

@Service
public class DirectorioRestServiceImpl implements DirectorioRestService {

    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public Persona findPersonaByIdentification(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion);
    }

    @Override
    public List<Persona> findPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public void deletePersonaByIdentification(String identificacion) {
        personaRepository.deleteByIdentificacion(identificacion);
    }

    @Override
    public Persona storePersona(Persona persona) {
        return personaRepository.save(persona);
    }
}
