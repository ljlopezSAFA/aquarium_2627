package es.safareyes.aquarium.conversores;


import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.modelos.Especie;
import org.mapstruct.Mapper;
import java.util.*;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

    EspecieDTO toDto(Especie especie);

    List<EspecieDTO> toDto(List<Especie> especie);




}
