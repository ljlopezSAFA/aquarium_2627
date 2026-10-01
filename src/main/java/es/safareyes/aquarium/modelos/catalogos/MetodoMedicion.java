package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=TIRAS, 2=GOTAS, 3=DIGITAL, 4=LABORATORIO */
@Entity
@Table(name = "cat_metodo_medicion")
@NoArgsConstructor
public class MetodoMedicion extends Catalogo {
}
