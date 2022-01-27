package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.Proveedor;
import ar.com.softtek.academytp.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getAllProveedores() {
        return (List<Proveedor>) this.proveedorRepository.findAll();
    }

    public Optional<Proveedor> getById(Integer id) {
        return this.proveedorRepository.findById(id);
    }

    public Proveedor saveProveedor(Proveedor proveedor) {
        return this.proveedorRepository.save(proveedor);
    }

    public boolean deleteProveedor(Integer id) {
        try {
            this.proveedorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
