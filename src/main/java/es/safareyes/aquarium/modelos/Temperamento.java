package es.safareyes.aquarium.modelos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cat_temperamento")
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Temperamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "codigo", length = 30)
    private String codigo;

    @Column(name = "nombre", length = 80, nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "orden")
    private Short orden;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

}
