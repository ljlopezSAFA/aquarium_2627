package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.AcuarioEquipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAcuarioEquipamientoRepository extends JpaRepository<AcuarioEquipamiento,Integer> {
}
