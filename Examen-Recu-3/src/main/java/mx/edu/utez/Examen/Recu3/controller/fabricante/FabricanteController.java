package mx.edu.utez.Examen.Recu3.controller.fabricante;


import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import mx.edu.utez.Examen.Recu3.service.fabricante.FabricanteService;
import mx.edu.utez.Examen.Recu3.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-recu/fabricante/")
@CrossOrigin(origins = {"*"})
public class FabricanteController {
    @Autowired
    private FabricanteService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Fabricante>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Fabricante>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Fabricante>> insert(
            @RequestBody FabricanteDto fabricanteDto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(fabricanteDto.getFabricante()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/searchById/")
    public ResponseEntity<List<Fabricante>> searchFabricanteById() {
        List<Fabricante> fabricantes = service.searchFabricanteById();
        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }

    @GetMapping("/searchNombre/")
    public ResponseEntity<List<Fabricante>> searchFabricanteByNombre() {
        List<Fabricante> fabricantes = service.searchFabricanteByNombre();
        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }

    @GetMapping("/searchFabricante/")
    public ResponseEntity<List<Fabricante>> searchFabricante() {
        List<Fabricante> fabricantes = service.searchFabricanteByNombreAndProducto();
        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }
}
