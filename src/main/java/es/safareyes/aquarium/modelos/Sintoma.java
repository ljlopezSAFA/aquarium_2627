package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.ZonaSintoma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sintoma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true, length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zona", nullable = false)
    private ZonaSintoma zona;

    @Column(name = "descripcion")
    private String descripcion;
}
