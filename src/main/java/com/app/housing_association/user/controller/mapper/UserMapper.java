package com.app.housing_association.user.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.user.controller.dto.UserBuildingDto;
import com.app.housing_association.user.controller.dto.UserContractDto;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.dto.UserWithChangingPasswordDto;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.entity.model.UserWithBuilding;
import com.app.housing_association.user.entity.model.UserWithChangingPassword;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User,UserDto,Long> {


    @Mapping(source = "password", target = "password")
    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "toDto")
    @Override
    User toEntity(UserDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @BeanMapping(ignoreByDefault = true)
    @Override
    UserDto toDto(User entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "buildingId", target = "buildingId")
    @BeanMapping(ignoreByDefault = true)
    UserBuildingDto toUserBuildingDto(UserWithBuilding entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @BeanMapping(ignoreByDefault = true)
    UserDto toRegisteredDto(User entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "contract", target = "contract",qualifiedByName ="basicContractMapper")
    @BeanMapping(ignoreByDefault = true)
    UserContractDto toUserContractDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "contract", target = "contract",qualifiedByName ="fullContractMapper")
    @BeanMapping(ignoreByDefault = true)
    UserContractDto toUserContractWithDetailsDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amountPeople", target = "amountPeople")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "finishTime", target = "finishTime")
    @Mapping(source = "fee", target = "fee")
    @Mapping(source = "flat", target = "flat")
    @BeanMapping(ignoreByDefault = true)
    @Named("fullContractMapper")
    ContractDto toFullContractDto(Contract contract);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "amountPeople", target = "amountPeople")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "finishTime", target = "finishTime")
    @BeanMapping(ignoreByDefault = true)
    @Named("basicContractMapper")
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

    @Mapping(source = "id", target = "id")
    @Mapping(source = "storey", target = "storey")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "nrStaircase", target = "nrStaircase")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    FlatDto toFlatDto(Flat flat);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "town", target = "town")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "zipCode", target = "zipCode")
    @BeanMapping(ignoreByDefault = true)
    BuildingDto toBuildingDto(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "newPassword", target = "newPassword")
    @BeanMapping(ignoreByDefault = true)
    UserWithChangingPassword toEntityWithChangingPassword(UserWithChangingPasswordDto dto);

}
