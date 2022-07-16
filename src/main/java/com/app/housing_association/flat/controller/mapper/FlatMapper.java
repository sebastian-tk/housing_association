package com.app.housing_association.flat.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.flat.controller.dto.FlatContractDto;
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
    @Mapping(source = "nrStaircase", target = "nrStaircase")
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

    @Mapping(source = "id", target = "id")
    @Mapping(source = "storey", target = "storey")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "nrStaircase", target = "nrStaircase")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "typeUse", target = "typeUse")
    @Mapping(source = "contract", target = "contract")
    @BeanMapping(ignoreByDefault = true)
    FlatContractDto toFlatContractDto(Flat entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amountPeople", target = "amountPeople")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "finishTime", target = "finishTime")
    @Mapping(source = "fee", target = "fee")
    @BeanMapping(ignoreByDefault = true)
    ContractDto toContractDto(Contract contract);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "heating", target = "heating")
    @Mapping(source = "renovationFund", target = "renovationFund")
    @Mapping(source = "rent", target = "rent")
    @Mapping(source = "exploitation", target = "exploitation")
    @Mapping(source = "administration", target = "administration")
    @Mapping(source = "warmWater", target = "warmWater")
    @Mapping(source = "coldWater", target = "coldWater")
    @Mapping(source = "sewage", target = "sewage")
    @Mapping(source = "rubbish", target = "rubbish")
    @Mapping(source = "total", target = "total")
    @BeanMapping(ignoreByDefault = true)
    FeeDto toFeeDto(Fee fee);
}
