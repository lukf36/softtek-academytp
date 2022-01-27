package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.dto.LoginRequest;
import ar.com.softtek.academytp.models.Cliente;
import ar.com.softtek.academytp.models.Orden;
import ar.com.softtek.academytp.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public List<Cliente> getAllClientes(){
        return this.clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public Optional<Cliente> getClienteById(@PathVariable Integer id){
        return this.clienteService.getClienteById(id);
    }

    @GetMapping("/{id}/ordenes")
    @PreAuthorize("hasRole('CLIENTE')")
    public List<Orden> getAllOrdenesFromClienteById(@PathVariable Integer id){
        return this.clienteService.getAllOrdenesFromClienteById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return this.clienteService.saveCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteCliente(@PathVariable Integer id){
        return this.clienteService.deleteCliente(id);
    }

}
