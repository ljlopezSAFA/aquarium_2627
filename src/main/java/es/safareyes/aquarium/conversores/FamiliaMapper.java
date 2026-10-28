package es.safareyes.aquarium.conversores;

import es.safareyes.aquarium.dtos.FamiliaDTO;
import es.safareyes.aquarium.modelos.Familia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamiliaMapper {


    Familia toEntity(FamiliaDTO dto);
    FamiliaDTO toDTO(Familia entity);
}
