package com.app.housing_association.vote.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.vote.controller.dto.VoteBaseDto;
import com.app.housing_association.vote.controller.dto.VoteDto;
import com.app.housing_association.vote.entity.Vote;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper extends GenericMapper<Vote, VoteDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "dateFinish", target = "dateFinish")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Vote toEntity(VoteDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(source = "upVote", target = "upVote")
    @Mapping(source = "downVote", target = "downVote")
    @Mapping(source = "users", target = "users")
    @Mapping(source = "finished", target = "finished")
    @Override
    VoteDto toDto(Vote entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "dateFinish", target = "dateFinish")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    VoteDto createToDto(Vote entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "dateFinish", target = "dateFinish")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    VoteDto putToDto(Vote entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "upVote", target = "upVote")
    @Mapping(source = "downVote", target = "downVote")
    @Mapping(source = "finished", target = "finished")
    @BeanMapping(ignoreByDefault = true)
    VoteBaseDto toBaseDto(Vote entity);

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

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @BeanMapping(ignoreByDefault = true)
    UserDto toUserDto(User entity);
}

