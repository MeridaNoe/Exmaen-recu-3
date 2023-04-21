package mx.edu.utez.Examen.Recu3.model.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Examen.Recu3.model.fabricante.Fabricante;
import javax.persistence.*;

@Entity
@Table(name = "producto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "fabricante_id", nullable = false, referencedColumnName = "id")
    private Fabricante fabricante;
}
