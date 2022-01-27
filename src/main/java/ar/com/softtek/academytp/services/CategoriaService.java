package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.Categoria;
import ar.com.softtek.academytp.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return (List<Categoria>) this.categoriaRepository.findAll();
    }

    public Optional<Categoria> getById(Integer id) {
        return this.categoriaRepository.findById(id);
    }

    public Categoria saveCategoria(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public boolean deleteCategoria(Integer id) {
        try {
            this.categoriaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
