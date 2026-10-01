package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=FACIL, 2=MEDIA, 3=DIFICIL, 4=EXPERTO */
@Entity
@Table(name = "cat_dificultad")
@NoArgsConstructor
public class Dificultad extends Catalogo {
}
