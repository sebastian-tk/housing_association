package com.app.housing_association.flat.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.entity.Flat;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlatMapper extends GenericMapper<Flat, FlatDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "storey", target = "storey")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "typeUse", target = "typeUse")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Flat toEntity(FlatDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    FlatDto toDto(Flat entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "town", target = "town")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "number", target = "number")
    @BeanMapping(ignoreByDefault = true)
    BuildingDto createBuildingDto(Building entity);

    @InheritInverseConfiguration(name="createBuildingDto")
    Building createBuildingEntity(BuildingDto dto);
}
