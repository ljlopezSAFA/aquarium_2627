package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=USUARIO, 2=EDITOR, 3=ADMIN */
@Entity
@Table(name = "cat_rol_usuario")
@NoArgsConstructor
public class RolUsuario extends Catalogo {
}
