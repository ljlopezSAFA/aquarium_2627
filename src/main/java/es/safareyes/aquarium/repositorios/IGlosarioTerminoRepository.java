package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.GlosarioTermino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGlosarioTerminoRepository extends JpaRepository<GlosarioTermino,Integer> {
}
