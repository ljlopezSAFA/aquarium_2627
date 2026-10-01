package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=DELANTERA, 2=MEDIA, 3=TRASERA, 4=SUPERFICIE */
@Entity
@Table(name = "cat_posicion_planta")
@NoArgsConstructor
public class PosicionPlanta extends Catalogo {
}
