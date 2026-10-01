package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EnfermedadMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnfermedadMedicamentoRepository extends JpaRepository<EnfermedadMedicamento,Integer> {
}
