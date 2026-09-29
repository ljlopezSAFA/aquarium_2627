package es.safareyes.aquarium.repositorios;

import es.safareyes.aquarium.modelos.Continente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContinenteRepository extends JpaRepository<Continente, Integer> {

}
