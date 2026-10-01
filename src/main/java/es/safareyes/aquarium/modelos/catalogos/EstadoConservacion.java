package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=NE, 2=DD, 3=LC, 4=NT, 5=VU, 6=EN, 7=CR, 8=EW, 9=EX */
@Entity
@Table(name = "cat_estado_conservacion")
@NoArgsConstructor
public class EstadoConservacion extends Catalogo {
}
