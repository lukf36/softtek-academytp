package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.models.DetalleOrden;
import ar.com.softtek.academytp.services.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/detalleorden")
public class DetalleOrdenController {
    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @GetMapping
    public List<DetalleOrden> getAllDetalleOrdenes(){
        return this.detalleOrdenService.getAllDetalleOrdenes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
    public Optional<DetalleOrden> getDetalleOrdenById(@PathVariable Integer id){
        return this.detalleOrdenService.getDetalleOrdenById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
    public DetalleOrden saveDetalleOrden(@RequestBody DetalleOrden detalleOrden){
        return this.detalleOrdenService.saveDetalleOrden(detalleOrden);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public boolean deeleteDetalleOrden(@PathVariable Integer id){
        return this.detalleOrdenService.deleteDetalleOrden(id);
    }
}
