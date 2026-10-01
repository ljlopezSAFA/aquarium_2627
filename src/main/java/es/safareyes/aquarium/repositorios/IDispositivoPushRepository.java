package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.DispositivoPush;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDispositivoPushRepository extends JpaRepository<DispositivoPush,Integer> {
}
