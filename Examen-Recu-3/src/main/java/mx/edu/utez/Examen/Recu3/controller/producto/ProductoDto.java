package mx.edu.utez.Examen.Recu3.controller.producto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import mx.edu.utez.Examen.Recu3.model.producto.Producto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoDto {
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 1, max = 100)
    private String nombre;
    private Double precio;

    private Fabricante fabricante;

    public Producto getProducto(){
        return new Producto(
                getId(),
                getNombre(),
                getPrecio(),
                getFabricante()
        );
    }
}
