package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFamiliaRepository extends JpaRepository<Familia,Integer> {
}
