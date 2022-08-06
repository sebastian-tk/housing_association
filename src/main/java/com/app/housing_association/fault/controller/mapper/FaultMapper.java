package com.app.housing_association.fault.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.fault.controller.dto.FaultBaseDto;
import com.app.housing_association.fault.controller.dto.FaultDto;
import com.app.housing_association.fault.entity.Fault;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FaultMapper extends GenericMapper<Fault, FaultDto, Long> {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "fixed", target = "fixed")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    @Override
    FaultDto toDto(Fault entity);

    @InheritInverseConfiguration(name = "toDto")
    @Override
    Fault toEntity(FaultDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "fixed", target = "fixed")
    @BeanMapping(ignoreByDefault = true)
    FaultBaseDto toBaseDto(Fault entity);

    @InheritInverseConfiguration(name = "toBaseDto")
    Fault toBaseEntity(FaultBaseDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "town", target = "town")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "zipCode", target = "zipCode")
    @BeanMapping(ignoreByDefault = true)
    Building toBuilding(BuildingDto dto);

    @InheritInverseConfiguration(name = "toBuilding")
    BuildingDto toBuildingDto(Building entity);
}

