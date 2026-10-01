package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=PIEL, 2=ALETAS, 3=BRANQUIAS, 4=OJOS, 5=VIENTRE, 6=BOCA, 7=COMPORTAMIENTO, 8=GENERAL */
@Entity
@Table(name = "cat_zona_sintoma")
@NoArgsConstructor
public class ZonaSintoma extends Catalogo {
}
