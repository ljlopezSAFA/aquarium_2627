package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=BACTERIA, 2=HONGO, 3=PARASITO_EXTERNO, 4=PARASITO_INTERNO, 5=VIRUS, 6=AMBIENTAL, 7=NUTRICIONAL, 8=DESCONOCIDO */
@Entity
@Table(name = "cat_agente_enfermedad")
@NoArgsConstructor
public class AgenteEnfermedad extends Catalogo {
}
