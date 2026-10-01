package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=C, 2=F */
@Entity
@Table(name = "cat_unidad_temperatura")
@NoArgsConstructor
public class UnidadTemperatura extends Catalogo {
}
