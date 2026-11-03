package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.EstadoConservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoConservacionRepository extends JpaRepository<EstadoConservacion, Short> {
}
