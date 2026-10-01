package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=ANDROID, 2=IOS, 3=WEB */
@Entity
@Table(name = "cat_plataforma")
@NoArgsConstructor
public class Plataforma extends Catalogo {
}
