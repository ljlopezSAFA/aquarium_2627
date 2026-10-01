package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.Continente;
import es.safareyes.aquarium.modelos.catalogos.TipoAgua;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "biotopo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Biotopo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true, length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_continente", nullable = false)
    private Continente continente;

    // En BD: DEFAULT 1 (DULCE). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_agua", nullable = false)
    private TipoAgua tipoAgua;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;
}
