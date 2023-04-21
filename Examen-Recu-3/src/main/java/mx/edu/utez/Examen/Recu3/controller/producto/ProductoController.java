package mx.edu.utez.Examen.Recu3.controller.producto;

import mx.edu.utez.Examen.Recu3.controller.fabricante.FabricanteDto;
import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import mx.edu.utez.Examen.Recu3.model.producto.Producto;
import mx.edu.utez.Examen.Recu3.service.producto.ProductoService;
import mx.edu.utez.Examen.Recu3.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-recu/producto/")
@CrossOrigin(origins = {"*"})
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Producto>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Producto>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Producto>> insert(
            @RequestBody ProductoDto productoDto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(productoDto.getProducto()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/searchProducto/")
    public ResponseEntity<List<Producto>> searchProducto(){
        List<Producto> productos = service.searchProducto();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Producto>> search(){
        List<Producto> productos = service.search();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
