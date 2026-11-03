package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.TipoReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoReproduccionRepository extends JpaRepository<TipoReproduccion, Short> {
}
