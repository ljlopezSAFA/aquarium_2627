package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicamentoRepository extends JpaRepository<Medicamento,Integer> {
}
