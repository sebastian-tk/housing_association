package com.app.housing_association.building.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.entity.Flat;
import org.mapstruct.*;

import java.util.Collections;

@Mapper(componentModel = "spring", imports = Collections.class)
public interface BuildingMapper extends GenericMapper<Building, BuildingDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "town", target = "town")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "zipCode", target = "zipCode")
    @Mapping(source = "yearConstruction", target = "yearConstruction")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "numberStoreys", target = "numberStoreys")
    @Mapping(source = "flatsPerStoreys", target = "flatsPerStoreys")
    @Mapping(source = "staircase", target = "staircase")
    @Mapping(
            target = "flats",
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            defaultExpression = "java(Collections.emptySet())")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Building toEntity(BuildingDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    BuildingDto toDto(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "storey", target = "storey")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "typeUse", target = "typeUse")
    @BeanMapping(ignoreByDefault = true)
    Flat createFlat(FlatDto dto);

    @InheritInverseConfiguration(name = "createFlat")
    FlatDto createFlatDto(Flat entity);
}
