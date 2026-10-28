package es.safareyes.aquarium.conversores;

import es.safareyes.aquarium.dtos.CrearFamiliaDTO;
import es.safareyes.aquarium.dtos.FamiliaDTO;
import es.safareyes.aquarium.modelos.Familia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring")
public interface FamiliaMapper {


    Familia toEntity(FamiliaDTO dto);

    @Mapping(source = "orden", target = "ordenFamilia")
    FamiliaDTO toDTO(Familia entity);

    List<Familia> toEntity(List<FamiliaDTO> dto);
    List<FamiliaDTO> toDTO(List<Familia> entity);


    @Mapping(target = "id",            ignore = true)
    @Mapping(target = "tipoOrganismo", ignore = true)
    @Mapping(target = "especies",      ignore = true)
    Familia toEntity(CrearFamiliaDTO dto);



}
