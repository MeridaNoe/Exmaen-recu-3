package mx.edu.utez.Examen.Recu3.model.fabricante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    boolean existsByNombre(String name);

    @Query(
            value ="select * from fabricante where id >= 4",
            nativeQuery = true
    )
    List<Fabricante> searchFabricanteById();

    @Query(
            value = "select * from fabricante;",
            nativeQuery = true
    )
    List<Fabricante> searchFabricanteByNombre();

    @Query(
            value = "SELECT f.nombre AS fabricante, COUNT(p.id) AS num_productos FROM fabricante f LEFT JOIN (SELECT id, fabricante_id FROM producto WHERE precio >= 220) p ON f.id = p.fabricante_id GROUP BY f.id ORDER BY f.nombre;",
            nativeQuery = true
    )
    List<Fabricante> searchFabricanteByNombreAAndProducto();

    boolean existsById(Long id);
}
