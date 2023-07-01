package com.example.SistemaVentasFacturas.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaVentasFacturas.Entity.Factura;
import com.example.SistemaVentasFacturas.Service.FacturaRestService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})

public class Ventas {
    @Autowired
    private FacturaRestService facturaService;
    private static final Logger logger = LoggerFactory.getLogger(Ventas.class);

    //Buscar factura por id
    @GetMapping("/factura/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Factura factura = null;
        Map<String,Object> response = new HashMap<>();

        try {
            factura = facturaService.findFacturaByPersona(id);

        }catch(DataAccessException e) {
            response.put("mensaje", "Error de acceso a la base de datos");
            response.put("error",e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }
        if(factura == null) {
            response.put("mensaje", "La factura no se encuentra en el sistema");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Factura>(factura,HttpStatus.OK);

    }

    //Guardar factura
    @PostMapping("/save/")
    public ResponseEntity<?>create(@RequestBody Factura factura) {
        logger.info("Contenido del JSON recibido: {}", factura);
        Factura facturaNueva = null;
        Map<String,Object> response = new HashMap<>();
        try {
            facturaNueva = facturaService.storeFactura(factura);

        }catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar la nueva factura en la base de datos");
            response.put("error",e.getMessage().concat(" ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La factura se ha creado con éxito");
        response.put("usuario", facturaNueva);

        return new ResponseEntity<Factura>(factura,HttpStatus.CREATED);
    }

}
