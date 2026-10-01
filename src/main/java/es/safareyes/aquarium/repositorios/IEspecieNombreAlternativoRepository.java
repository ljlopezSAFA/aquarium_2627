package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.EspecieNombreAlternativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecieNombreAlternativoRepository extends JpaRepository<EspecieNombreAlternativo,Integer> {
}
