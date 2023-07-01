package com.example.SistemaVentasFacturas.Service;

import java.util.List;
import com.example.SistemaVentasFacturas.Entity.Persona;
public interface DirectorioRestService {
    Persona findPersonaByIdentification(String identificacion);
    List<Persona>findPersonas();
    void deletePersonaByIdentification(String identificacion);

    Persona storePersona(Persona persona);


}
