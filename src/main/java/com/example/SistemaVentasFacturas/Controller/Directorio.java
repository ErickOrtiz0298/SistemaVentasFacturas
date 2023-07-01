package com.example.SistemaVentasFacturas.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaVentasFacturas.Entity.Persona;
import com.example.SistemaVentasFacturas.Service.DirectorioRestService;
@RestController
@RequestMapping("/directorio")
@CrossOrigin(origins = {"*"})
public class Directorio {
    private static final Logger logger = LoggerFactory.getLogger(Directorio.class);
    @Autowired
    private DirectorioRestService directorioService;


    //Encontrar a todas las personas
    @GetMapping("/list")
    public List<Persona>index(){
        return directorioService.findPersonas();
    }

    //Buscar persona por identificacion
    @GetMapping("/persona/{identificacion}")
    public ResponseEntity<?> show(@PathVariable String identificacion){
        Persona persona = null;
        Map<String,Object> response = new HashMap<>();

        try {
            persona = directorioService.findPersonaByIdentification(identificacion);

        }catch(DataAccessException e) {
            response.put("mensaje", "Error de acceso a la base de datos");
            response.put("error",e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }
        if(persona == null) {
            response.put("mensaje", "El usuario no se encuentra en el sistema");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona,HttpStatus.OK);

    }


    //Guardar persona
    @PostMapping("/save")
    public ResponseEntity<?>create(@RequestBody Persona persona) {
        logger.info("Contenido del JSON recibido: {}", persona);
        Persona personaNueva = null;
        Map<String,Object> response = new HashMap<>();
        try {
            personaNueva = directorioService.storePersona(persona);

        }catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar la persona en la base de datos");
            response.put("error",e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Usuario creado con éxito");
        response.put("usuario", personaNueva);

        return new ResponseEntity<Persona>(persona,HttpStatus.CREATED);
    }

    //Borrar persona por identificacion
    @Transactional
    @DeleteMapping("/delete/{identificacion}")
    public ResponseEntity<?>delete(@PathVariable String identificacion) {
        Map<String,Object> response = new HashMap<>();

        try {
            directorioService.deletePersonaByIdentification(identificacion);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la persona de la base de datos");
            response.put("error",e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Persona eliminada con éxito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

}
