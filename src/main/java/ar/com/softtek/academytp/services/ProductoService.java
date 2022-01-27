package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.Producto;
import ar.com.softtek.academytp.models.Promocion;
import ar.com.softtek.academytp.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PromocionService promocionService;

    public Long getProductCount(){
        return this.productoRepository.count();
    }

    public List<Producto> getAllProductos(
            Integer pageNo,
            Integer pageSize,
            String sortBy
    ) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Producto> pagedResult = this.productoRepository.findAll(paging);
        if (pagedResult.hasContent()){
            return this.applyDiscounts(pagedResult.getContent());
        }else{
            return new ArrayList<Producto>();
        }
    }

    public Optional<Producto> getById(Integer id) {
        return this.productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return this.productoRepository.save(producto);
    }

    public boolean deleteProductoById(Integer id) {
        try {
            this.productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<Producto> applyDiscounts(List<Producto> productos) {
        List<Producto> ret = new ArrayList<>();
        List<Promocion> promociones = this.promocionService.findPromocionesVigentes();
        for (Producto p: productos) {
            Integer id = p.getId();
            for (Promocion j: promociones) {
                if (id.equals(j.getProducto().getId())) {
                    p.setPrecio(max(p.getPrecio() - j.getDescuento(), 0));
                    p.setHasPromocion(true);
                }
            }
            ret.add(p);
        }
        return ret;
    }
}
