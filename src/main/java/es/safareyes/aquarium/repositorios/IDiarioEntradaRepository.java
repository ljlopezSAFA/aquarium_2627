package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.DiarioEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiarioEntradaRepository extends JpaRepository<DiarioEntrada,Integer> {
}
