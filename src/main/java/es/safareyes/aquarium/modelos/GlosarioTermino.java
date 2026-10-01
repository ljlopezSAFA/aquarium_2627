package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "glosario_termino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlosarioTermino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "termino", nullable = false, unique = true, length = 100)
    private String termino;

    @Column(name = "definicion", nullable = false)
    private String definicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
}
