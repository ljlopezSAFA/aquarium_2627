package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlimentoRepository extends JpaRepository<Alimento,Integer> {
}
