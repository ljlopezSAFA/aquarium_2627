package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.TokenRefresco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRefrescoRepository extends JpaRepository<TokenRefresco,Integer> {
}
