package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipamientoRepository extends JpaRepository<Equipamiento,Integer> {
}
