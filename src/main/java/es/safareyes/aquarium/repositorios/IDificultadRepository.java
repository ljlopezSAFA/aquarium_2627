package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.Dificultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDificultadRepository extends JpaRepository<Dificultad, Short> {
}
