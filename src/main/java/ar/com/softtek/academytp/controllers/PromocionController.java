package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.models.Promocion;
import ar.com.softtek.academytp.services.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/promocion")
public class PromocionController {
    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public List<Promocion> findAllPromociones(){
        return this.promocionService.getAllPromociones();
    }

    @GetMapping("/{id}")
    public Optional<Promocion> getPromocionById(@PathVariable Integer id){
        return this.promocionService.getPromocionById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Promocion savePromocion(@RequestBody Promocion promocion){
        return this.promocionService.savePromocion(promocion);
    }

    @GetMapping("/promocionesvigentes")
    public List<Promocion> getPromocionesVigentes() {
        return this.promocionService.findPromocionesVigentes();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Integer deletePromocion(@PathVariable Integer id) {
        if (this.promocionService.deletePromocion(id)) {
            return id;
        } else {
            return null;
        }
    }
}
