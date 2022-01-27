package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.Promocion;
import ar.com.softtek.academytp.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromocionService {
    @Autowired
    private PromocionRepository promocionRepository;

    public List<Promocion> getAllPromociones(){
        return (List<Promocion>) this.promocionRepository.findAll();
    }

    public Optional<Promocion> getPromocionById(Integer id){
        return this.promocionRepository.findById(id);
    }

    public Promocion savePromocion(Promocion promocion){
        return this.promocionRepository.save(promocion);
    }

    public List<Promocion> findPromocionesVigentes() {
        return this.promocionRepository.findAllByFechaVigenciaDesdeBeforeAndFechaVigenciaHastaIsAfter(
                new Date(),
                new Date()
        );
    }

    public boolean deletePromocion(Integer id){
        try {
            this.promocionRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
