package ar.com.softtek.academytp.repositories;

        import ar.com.softtek.academytp.models.Cliente;
        import org.springframework.data.repository.CrudRepository;

        import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    public Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);

    public Optional<Cliente> findByUsername(String username);

    public boolean existsByUsername(String username);
}
