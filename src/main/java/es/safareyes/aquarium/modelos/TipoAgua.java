package es.safareyes.aquarium.modelos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cat_tipo_agua")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TipoAgua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "orden")
    private Short orden;

    @Column(name = "activo")
    private Boolean activo;
}
