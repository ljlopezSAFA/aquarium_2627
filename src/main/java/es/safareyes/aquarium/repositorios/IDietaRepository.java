package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.catalogos.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDietaRepository extends JpaRepository<Dieta, Short> {
}
