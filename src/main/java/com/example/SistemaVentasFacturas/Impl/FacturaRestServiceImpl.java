package com.example.SistemaVentasFacturas.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SistemaVentasFacturas.Entity.Factura;
import com.example.SistemaVentasFacturas.Service.FacturaRestService;
import com.example.SistemaVentasFacturas.Repository.FacturaRepository;

@Service
public class FacturaRestServiceImpl implements FacturaRestService{
    @Autowired
    private FacturaRepository facturaRepository;
    @Override
    public Factura findFacturaByPersona(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public Factura storeFactura(Factura factura) {
        return facturaRepository.save(factura);
    }
}
