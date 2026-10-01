package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=LEVE, 2=MODERADA, 3=GRAVE */
@Entity
@Table(name = "cat_gravedad")
@NoArgsConstructor
public class Gravedad extends Catalogo {
}
