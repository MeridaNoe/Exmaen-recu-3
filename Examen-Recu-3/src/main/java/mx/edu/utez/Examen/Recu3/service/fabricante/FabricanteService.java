package mx.edu.utez.Examen.Recu3.service.fabricante;

import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import mx.edu.utez.Examen.Recu3.model.fabricante.FabricanteRepository;
import mx.edu.utez.Examen.Recu3.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class FabricanteService {
    @Autowired
    private FabricanteRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Fabricante>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Fabricante> getOne(Long id) {
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Fabricante> insert(Fabricante fabricante) {
        if (this.repository.existsByNombre(fabricante.getNombre())) {
            return new CustomResponse<>(
                    null, true, 400, "Esta fabricante ya esta registrado"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(fabricante),
                false,
                200,
                "Fabricante registrado con exito"

        );
    }

    public List<Fabricante> searchFabricanteById() {
        return repository.searchFabricanteById();
    }

    public List<Fabricante> searchFabricanteByNombre() {
        return repository.searchFabricanteByNombre();
    }

    public List<Fabricante> searchFabricanteByNombreAndProducto() {
        return repository.searchFabricanteByNombreAAndProducto();
    }

}
