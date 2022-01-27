package ar.com.softtek.academytp.repositories;

import ar.com.softtek.academytp.models.Promocion;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PromocionRepository extends CrudRepository<Promocion, Integer> {
    public List<Promocion> findAllByFechaVigenciaDesdeBeforeAndFechaVigenciaHastaIsAfter(
            Date fechaVigenciaDesde,
            Date fechaVigenciaHasta
    );
}
