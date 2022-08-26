package com.app.housing_association.fault.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.fault.controller.dto.FaultBaseDto;
import com.app.housing_association.fault.controller.dto.FaultDto;
import com.app.housing_association.fault.entity.Fault;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FaultMapper extends GenericMapper<Fault, FaultDto, Long> {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "fixed", target = "fixed")
    @Mapping(source = "building", target = "building",qualifiedByName = "toBuildingDto")
    @BeanMapping(ignoreByDefault = true)
    @Override
    FaultDto toDto(Fault entity);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(source = "building", target = "building",qualifiedByName = "toBuilding")
    @Override
    Fault toEntity(FaultDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "fixed", target = "fixed")
    @BeanMapping(ignoreByDefault = true)
    FaultBaseDto toBaseDto(Fault entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "fixed", target = "fixed")
    @Mapping(source = "building", target = "building", qualifiedByName = "toBaseDtoBuilding")
    @BeanMapping(ignoreByDefault = true)
    FaultBaseDto toBaseDtoForAll(Fault entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @BeanMapping(ignoreByDefault = true)
    @Named("toBaseDtoBuilding")
    BuildingDto toBaseDtoBuilding(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "town", target = "town")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "zipCode", target = "zipCode")
    @BeanMapping(ignoreByDefault = true)
    @Named("toBuilding")
    Building toBuilding(BuildingDto dto);

    @InheritInverseConfiguration(name = "toBuilding")
    @Named("toBuildingDto")
    BuildingDto toBuildingDto(Building entity);
}

