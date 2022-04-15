package com.app.housing_association.user.controller.mapper;

import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User,UserDto,Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @BeanMapping(ignoreByDefault = true)
    @Override
    User toEntity(UserDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    UserDto toDto(User entity);
}
