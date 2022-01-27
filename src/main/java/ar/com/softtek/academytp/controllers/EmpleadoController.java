package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.models.Empleado;
import ar.com.softtek.academytp.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<Empleado> getAllEmpleados(){
        return this.empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Optional<Empleado> getEmpleadoById(@PathVariable Integer id){
        return this.empleadoService.getEmpleadoById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Empleado saveEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.saveEmpleado(empleado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Integer deleteEmpleado(@PathVariable Integer id){
        if (this.empleadoService.deleteEmpleado(id)){
            return id;
        } else {
            return null;
        }
    }
}
