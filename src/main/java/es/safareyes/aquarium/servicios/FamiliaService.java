package es.safareyes.aquarium.servicios;

import es.safareyes.aquarium.ElementoNoEncontrado;
import es.safareyes.aquarium.conversores.ElementoDuplicado;
import es.safareyes.aquarium.conversores.FamiliaMapper;
import es.safareyes.aquarium.dtos.CrearFamiliaDTO;
import es.safareyes.aquarium.dtos.FamiliaDTO;
import es.safareyes.aquarium.modelos.Familia;
import es.safareyes.aquarium.modelos.catalogos.TipoOrganismo;
import es.safareyes.aquarium.repositorios.IFamiliaRepository;
import es.safareyes.aquarium.repositorios.ITipoOrganismoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FamiliaService {

    private IFamiliaRepository repository;
    private FamiliaMapper mapper;
    private ITipoOrganismoRepository tipoOrganismoRepository;


    public List<FamiliaDTO> consultarTodas() {
        return mapper.toDTO(repository.findAll());
    }


    public FamiliaDTO findById(Integer id) {
        Familia f = repository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontrado("No se ha encontrado la Familia con el id" + id));

        return mapper.toDTO(f);
    }

    public void eliminar(Integer id) {
        Familia f = repository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontrado("No se ha encontrado la Familia con el id" + id));

        repository.delete(f);
        log.info("Se ha eliminado la familia con id" + id);
    }


    @Transactional
    public FamiliaDTO crear(CrearFamiliaDTO dto) {

        if (repository.existsByNombreIgnoreCase(dto.nombre())) {
            throw new ElementoDuplicado("Ya existe una familia con el nombre " + dto.nombre());
        }

        TipoOrganismo tipoOrganismo = tipoOrganismoRepository.findById(dto.idOrganismo())
                .orElseThrow(() -> new ElementoNoEncontrado(
                        "No existe el tipo de organismo con el id " + dto.idOrganismo()));

        Familia familia = mapper.toEntity(dto);
        familia.setTipoOrganismo(tipoOrganismo);

        return mapper.toDTO(repository.save(familia));
    }


}
