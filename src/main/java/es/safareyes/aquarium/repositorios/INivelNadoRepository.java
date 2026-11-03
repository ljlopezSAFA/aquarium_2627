package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.NivelNado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INivelNadoRepository extends JpaRepository<NivelNado, Short> {
}
