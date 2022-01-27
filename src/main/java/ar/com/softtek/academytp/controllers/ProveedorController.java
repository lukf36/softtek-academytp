package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.models.Proveedor;
import ar.com.softtek.academytp.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping()
    public List<Proveedor> getAllProveedores() {
        return this.proveedorService.getAllProveedores();
    }

    @GetMapping("/{id}")
    public Optional<Proveedor> getById(@PathVariable Integer id){
        return this.proveedorService.getById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor){
        return this.proveedorService.saveProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Integer deleteProveedr(@PathVariable Integer id){
        if (this.proveedorService.deleteProveedor(id)) {
            return id;
        } else {
            return null;
        }
    }
}
