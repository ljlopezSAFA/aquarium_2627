package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=DEPREDACION, 2=AGRESIVIDAD, 3=PARAMETROS_AGUA, 4=TAMANO, 5=COMPETENCIA_ALIMENTO, 6=MORDISQUEO_ALETAS, 7=COME_PLANTAS, 8=OTRO */
@Entity
@Table(name = "cat_motivo_compatibilidad")
@NoArgsConstructor
public class MotivoCompatibilidad extends Catalogo {
}
