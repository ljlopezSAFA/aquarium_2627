package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=CICLANDO, 2=ACTIVO, 3=INACTIVO, 4=DESMONTADO */
@Entity
@Table(name = "cat_estado_acuario")
@NoArgsConstructor
public class EstadoAcuario extends Catalogo {
}
