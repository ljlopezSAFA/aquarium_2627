package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EspeciePlanta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspeciePlantaRepository extends JpaRepository<EspeciePlanta,Integer> {
}
