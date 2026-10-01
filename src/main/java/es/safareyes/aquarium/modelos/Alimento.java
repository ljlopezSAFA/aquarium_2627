package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.TipoAlimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alimento", uniqueConstraints = {
        @UniqueConstraint(name = "ux_alimento_nombre_marca", columnNames = {"nombre", "id_marca"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoAlimento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;
}
