package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnfermedadRepository extends JpaRepository<Enfermedad,Integer> {
}
