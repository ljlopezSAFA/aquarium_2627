package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EpisodioSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEpisodioSaludRepository extends JpaRepository<EpisodioSalud,Integer> {
}
