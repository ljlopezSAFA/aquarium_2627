package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_nombre_alternativo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecieNombreAlternativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "idioma", nullable = false, length = 5)
    private String idioma = "es";

    @Column(name = "es_sinonimo_cientifico", nullable = false)
    private Boolean esSinonimoCientifico = false;
}
