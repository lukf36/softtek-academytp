package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.models.Producto;
import ar.com.softtek.academytp.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://retail-lf-softtek.herokuapp.com/", maxAge = 3600)
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //@GetMapping()
    //public List<Producto> getAllProductos() {
    //    return this.productoService.getAllProductos();
    //}

    @GetMapping("/count")
    public Long getProductCount(){
        return this.productoService.getProductCount();
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        List<Producto> ret = this.productoService.getAllProductos(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Producto>>(ret, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Producto> getById(@PathVariable Integer id) {
        return this.productoService.getById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Producto saveProducto(@Valid @RequestBody Producto producto) {
        return this.productoService.saveProducto(producto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public Integer deleteProducto(@PathVariable Integer id) {
        if (this.productoService.deleteProductoById(id)) {
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
