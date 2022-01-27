package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.Cliente;
import ar.com.softtek.academytp.models.Orden;
import ar.com.softtek.academytp.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return (List<Cliente>) this.clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id){
        return this.clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    public Optional<Cliente> findByNombreAndApellido(String nombre, String apellido) {
        return this.clienteRepository.findByNombreAndApellido(nombre, apellido);
    }

    public List<Orden> getAllOrdenesFromClienteById(Integer id) {
        Optional<Cliente> oc = this.clienteRepository.findById(id);
        if (oc.isPresent()){
            return oc.get().getOrdenes();
        } else {
            return new ArrayList<>();
        }
    }

    public boolean deleteCliente(Integer id){
        try {
            this.clienteRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
