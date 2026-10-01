package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.TareaProgramada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITareaProgramadaRepository extends JpaRepository<TareaProgramada,Integer> {
}
