package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.TipoOrganismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoOrganismoRepository extends JpaRepository<TipoOrganismo,Short> {
}
