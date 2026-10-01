package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=PACIFICO, 2=SEMI_AGRESIVO, 3=AGRESIVO, 4=TERRITORIAL */
@Entity
@Table(name = "cat_temperamento")
@NoArgsConstructor
public class Temperamento extends Catalogo {
}
