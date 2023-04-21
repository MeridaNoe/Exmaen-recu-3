package mx.edu.utez.Examen.Recu3.controller.fabricante;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import mx.edu.utez.Examen.Recu3.model.producto.Producto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FabricanteDto {
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 1, max = 100)
    private String nombre;

    private List<Producto> producto;


    public Fabricante getFabricante() {
        return new Fabricante(
                getId(),
                getNombre(),
                getProducto()
        );
    }
}
