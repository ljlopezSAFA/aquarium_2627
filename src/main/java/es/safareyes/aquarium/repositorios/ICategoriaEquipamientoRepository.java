package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.CategoriaEquipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaEquipamientoRepository extends JpaRepository<CategoriaEquipamiento,Integer> {
}
