package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.DetalleOrden;
import ar.com.softtek.academytp.repositories.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService {
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public List<DetalleOrden> getAllDetalleOrdenes(){
        return (List<DetalleOrden>) this.detalleOrdenRepository.findAll();
    }

    public Optional<DetalleOrden> getDetalleOrdenById(Integer id){
        return this.detalleOrdenRepository.findById(id);
    }

    public DetalleOrden saveDetalleOrden(DetalleOrden detalleOrden){
        return this.detalleOrdenRepository.save(detalleOrden);
    }

    public boolean deleteDetalleOrden(Integer id){
        try {
            this.detalleOrdenRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
