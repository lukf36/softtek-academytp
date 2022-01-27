package ar.com.softtek.academytp.repositories;

import ar.com.softtek.academytp.models.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
}
