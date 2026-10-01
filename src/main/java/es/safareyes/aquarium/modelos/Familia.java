package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.TipoOrganismo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity
@Table(name = "familia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "orden", length = 100)
    private String orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_organismo", nullable = false)
    private TipoOrganismo tipoOrganismo;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY)
    private List<Especie> especies;



}
