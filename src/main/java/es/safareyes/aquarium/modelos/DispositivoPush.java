package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.Plataforma;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dispositivo_push")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoPush {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "token_push", nullable = false, unique = true, length = 500)
    private String tokenPush;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plataforma", nullable = false)
    private Plataforma plataforma;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "ultimo_uso")
    private LocalDateTime ultimoUso;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;
}
