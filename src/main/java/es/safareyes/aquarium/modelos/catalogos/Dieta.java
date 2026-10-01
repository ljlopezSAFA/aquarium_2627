package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=HERBIVORO, 2=CARNIVORO, 3=OMNIVORO, 4=DETRITIVORO, 5=PLANCTIVORO */
@Entity
@Table(name = "cat_dieta")
@NoArgsConstructor
public class Dieta extends Catalogo {
}
