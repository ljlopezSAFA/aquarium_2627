package es.safareyes.aquarium.conversores;


import es.safareyes.aquarium.dtos.CrearEspeciePezDTO;
import es.safareyes.aquarium.dtos.EditarEspeciePezDTO;
import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.EspeciePez;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FamiliaMapper.class, BiotopoMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EspecieMapper {


    @Mapping(source = "dificultad.nombre", target = "dificultad")
    EspecieDTO toDto(Especie especie);

    List<EspecieDTO> toDto(List<Especie> especie);

    EspeciePez toEntity(CrearEspeciePezDTO dto);


    void actualizar(@MappingTarget EspeciePez especiePez, EditarEspeciePezDTO dto);



}
