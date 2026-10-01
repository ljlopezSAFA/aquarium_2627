package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISintomaRepository extends JpaRepository<Sintoma,Integer> {
}
