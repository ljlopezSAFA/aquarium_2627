package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.MedicionAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicionAguaRepository extends JpaRepository<MedicionAgua,Integer> {
}
