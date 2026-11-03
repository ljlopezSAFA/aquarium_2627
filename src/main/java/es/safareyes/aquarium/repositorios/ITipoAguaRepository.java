package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.TipoAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoAguaRepository extends JpaRepository<TipoAgua, Short> {
}
