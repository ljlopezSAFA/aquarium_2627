package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EspeciePez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspeciePezRepository extends JpaRepository<EspeciePez,Integer> {
}
