package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.AcuarioHabitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAcuarioHabitanteRepository extends JpaRepository<AcuarioHabitante,Integer> {
}
