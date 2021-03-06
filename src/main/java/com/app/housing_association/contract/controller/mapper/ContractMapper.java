package com.app.housing_association.contract.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.entity.User;
import org.mapstruct.*;

import java.util.Collections;

@Mapper(componentModel = "spring")
public interface ContractMapper extends GenericMapper<Contract, ContractDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amountPeople", target = "amountPeople")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "finishTime", target = "finishTime")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "flat", target = "flat")
    @Mapping(source = "fee", target = "fee")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Contract toEntity(ContractDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    ContractDto toDto(Contract entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password", target = "password")
    @BeanMapping(ignoreByDefault = true)
    User createUser(UserDto dto);

    @InheritInverseConfiguration(name = "createUser")
    UserDto createUserDto(User entity);

    @Mapping(source = "id", target = "id")
    @BeanMapping(ignoreByDefault = true)
    Flat createFlat(FlatDto dto);

    @InheritInverseConfiguration(name = "createFlat")
    FlatDto createFlatDto(Flat entity);

    @Mapping(source = "id", target = "id")
    @BeanMapping(ignoreByDefault = true)
    Building createBuilding(BuildingDto dto);

    @InheritInverseConfiguration(name = "createBuilding")
    BuildingDto createBuildingDto(Building entity);

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
    Fee createFee(FeeDto dto);

    @InheritInverseConfiguration(name = "createFee")
    FeeDto createFeeDto(Fee entity);
}
