package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.models.Categoria;
import ar.com.softtek.academytp.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping()
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public List<Categoria> getAllCategorias() {
        return this.categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public Optional<Categoria> getById(@PathVariable Integer id) {
        return this.categoriaService.getById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public Categoria saveCategoria(@Valid @RequestBody Categoria categoria) {
        return this.categoriaService.saveCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
    public Integer deleteCategoria(@PathVariable Integer id) {
        if (this.categoriaService.deleteCategoria(id)) {
            return id;
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
