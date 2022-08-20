package com.app.housing_association.contract.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper extends GenericMapper<Contract, ContractDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amountPeople", target = "amountPeople")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "finishTime", target = "finishTime")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "flat", target = "flat")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Contract toEntity(ContractDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(source = "fee", target = "fee")
    @Override
    ContractDto toDto(Contract entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @BeanMapping(ignoreByDefault = true)
    UserDto createUserDto(User entity);

    @InheritInverseConfiguration(name = "createUserDto")
    @Mapping(source = "password", target = "password")
    User createUser(UserDto dto);

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
