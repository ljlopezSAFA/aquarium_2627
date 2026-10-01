package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EspecieImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecieImagenRepository extends JpaRepository<EspecieImagen,Integer> {
}
