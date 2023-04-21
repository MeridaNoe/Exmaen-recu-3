package mx.edu.utez.Examen.Recu3.service.producto;

import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import mx.edu.utez.Examen.Recu3.model.producto.Producto;
import mx.edu.utez.Examen.Recu3.model.producto.ProductoRepository;
import mx.edu.utez.Examen.Recu3.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    @Autowired
    private ProductoRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Producto>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Producto> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,200,"OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Producto> insert(Producto producto){
        if (this.repository.existsByNombre(producto.getNombre())){
            return new CustomResponse<>(
                    null,true,400,"Este producto ya esta registrado"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAndFlush(producto),
                false,
                200,
                "Prodcuto registrado con exito"

        );
    }

    public List<Producto> searchProducto(){
        return repository.searchProductoByNombre();
    }

    public List<Producto> search(){
        return repository.searchProductByFabricante();
    }
}
