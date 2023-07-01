package com.example.SistemaVentasFacturas.Service;
import java.util.List;
import com.example.SistemaVentasFacturas.Entity.Factura;

public interface FacturaRestService {
    Factura findFacturaByPersona(Long id);
    Factura storeFactura(Factura factura);
}
