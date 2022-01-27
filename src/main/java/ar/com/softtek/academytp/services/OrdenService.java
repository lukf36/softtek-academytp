package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.dto.DetalleNewCompra;
import ar.com.softtek.academytp.dto.NewCompra;
import ar.com.softtek.academytp.models.Cliente;
import ar.com.softtek.academytp.models.DetalleOrden;
import ar.com.softtek.academytp.models.Orden;
import ar.com.softtek.academytp.models.Producto;
import ar.com.softtek.academytp.repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    DetalleOrdenService detalleOrdenService;

    @Autowired
    ClienteService clienteService;

    public List<Orden> getAllOrdenes(){
        return (List<Orden>) this.ordenRepository.findAll();
    }

    public Optional<Orden> getOrdenBytId(Integer id){
        return this.ordenRepository.findById(id);
    }

    public Orden saveOrden(Orden orden){
        return this.ordenRepository.save(orden);
    }

    public boolean deleteOrden(Integer id){
        try {
            this.ordenRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Orden generarOrden(NewCompra newCompra) {
        Optional<Cliente> oc = clienteService.getClienteById(newCompra.getClienteId());
        if (!oc.isPresent()){
            return null;
        }
        Cliente cliente = oc.get();
        Orden orden = new Orden();
        for (DetalleNewCompra c: newCompra.getDetalles()) {
            Integer prodId = c.getIdProdicto();
            Integer cantidad = c.getCantidad();
            Optional<Producto> op = productoService.getById(prodId);
            if (!op.isPresent()) {
                return null;
            }
            Producto p = op.get();
            if (p.getStock() < cantidad) {
                return null;
            }
            p.setStock(p.getStock() - cantidad);
            this.productoService.saveProducto(p);
            DetalleOrden detalle = new DetalleOrden();
            detalle.setCantidad(cantidad);
            detalle.setPrecio(p.getPrecio());
            detalle.setProducto(p);
            this.detalleOrdenService.saveDetalleOrden(detalle);
            orden.addDetalleOrden(detalle);
        }
        this.ordenRepository.save(orden);
        cliente.addOrden(orden);
        return orden;
    }
}
