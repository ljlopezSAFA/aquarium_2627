package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=SUPERFICIE, 2=MEDIO, 3=FONDO, 4=TODOS */
@Entity
@Table(name = "cat_nivel_nado")
@NoArgsConstructor
public class NivelNado extends Catalogo {
}
