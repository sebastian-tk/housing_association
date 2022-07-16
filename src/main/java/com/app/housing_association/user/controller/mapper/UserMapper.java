package com.app.housing_association.user.controller.mapper;

import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.user.controller.dto.UserContractDto;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User,UserDto,Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @BeanMapping(ignoreByDefault = true)
    @Override
    User toEntity(UserDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    UserDto toDto(User entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @BeanMapping(ignoreByDefault = true)
    UserDto toRegisteredDto(User entity);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "contract", target = "contract")
    @BeanMapping(ignoreByDefault = true)
    UserContractDto toUserContractDto(User user);

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
