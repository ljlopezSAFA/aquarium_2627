package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=MUERTE, 2=TRASLADO, 3=VENTA, 4=REGALO, 5=RETIRADA, 6=OTRO */
@Entity
@Table(name = "cat_motivo_baja")
@NoArgsConstructor
public class MotivoBaja extends Catalogo {
}
