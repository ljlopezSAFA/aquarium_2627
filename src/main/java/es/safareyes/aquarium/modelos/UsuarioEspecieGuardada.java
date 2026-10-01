package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.ListaGuardada;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "usuario_especie_guardada", uniqueConstraints = {
        @UniqueConstraint(name = "ux_usuario_especie_guardada", columnNames = {"id_usuario", "id_especie", "id_lista"})
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEspecieGuardada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    // En BD: DEFAULT 1 (FAVORITO). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lista", nullable = false)
    private ListaGuardada lista;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;
}
