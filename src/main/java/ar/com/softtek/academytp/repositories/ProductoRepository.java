package ar.com.softtek.academytp.repositories;

        import ar.com.softtek.academytp.models.Producto;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Integer> {
}
