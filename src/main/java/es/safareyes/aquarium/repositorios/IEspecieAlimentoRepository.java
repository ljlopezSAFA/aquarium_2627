package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EspecieAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecieAlimentoRepository extends JpaRepository<EspecieAlimento,Integer> {
}
