package ar.com.softtek.academytp.repositories;

import ar.com.softtek.academytp.models.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
    public Optional<Empleado> findByUsername(String username);

    public boolean existsByUsername(String username);
}
