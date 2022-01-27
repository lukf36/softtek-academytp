package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.dto.NewCompra;
import ar.com.softtek.academytp.models.Orden;
import ar.com.softtek.academytp.services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public List<Orden> getAllOrdenes(){
        return this.ordenService.getAllOrdenes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO') or hasRole('CLIENTE')")
    public Optional<Orden> getOrdenById(@PathVariable Integer id){
        return this.ordenService.getOrdenBytId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO') or hasRole('CLIENTE')")
    public Orden saveOrden(@RequestBody Orden orden){
        return this.ordenService.saveOrden(orden);
    }

    @PostMapping("/generarorden")
    @PreAuthorize("hasRole('CLIENTE')")
    public Orden generarOrden(@RequestBody NewCompra compra) {
        return this.ordenService.generarOrden(compra);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public boolean deleteOrden(@PathVariable Integer id){
        return this.ordenService.deleteOrden(id);
    }
}
