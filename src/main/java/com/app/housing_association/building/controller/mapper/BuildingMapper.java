package com.app.housing_association.building.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.fault.controller.dto.FaultDto;
import com.app.housing_association.fault.entity.Fault;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.notice.controller.dto.NoticeDto;
import com.app.housing_association.notice.entity.Notice;
import com.app.housing_association.vote.controller.dto.VoteDto;
import com.app.housing_association.vote.entity.Vote;
import org.mapstruct.*;

import java.util.Collections;

@Mapper(componentModel = "spring", imports = Collections.class)
public interface BuildingMapper extends GenericMapper<Building, BuildingDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "town", target = "town")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "zipCode", target = "zipCode")
    @Mapping(source = "yearConstruction", target = "yearConstruction")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "numberStoreys", target = "numberStoreys")
    @Mapping(source = "flatsPerStorey", target = "flatsPerStorey")
    @Mapping(source = "staircase", target = "staircase")
    @Mapping(
            target = "flats",
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            defaultExpression = "java(Collections.emptyList())")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Building toEntity(BuildingDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    BuildingDto toDto(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "storey", target = "storey")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "areaM2", target = "areaM2")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "nrStaircase", target = "nrStaircase")
    @BeanMapping(ignoreByDefault = true)
    Flat createFlat(FlatDto dto);

    @InheritInverseConfiguration(name = "createFlat")
    FlatDto createFlatDto(Flat entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "notices", target = "notices")
    @BeanMapping(ignoreByDefault = true)
    BuildingDto toDtoWithNotices(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "archived", target = "archived")
    @Mapping(source = "executionDate", target = "executionDate")
    @BeanMapping(ignoreByDefault = true)
    NoticeDto toNoticeDto(Notice entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "faults", target = "faults")
    @BeanMapping(ignoreByDefault = true)
    BuildingDto toDtoWithFaults(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "fixed", target = "fixed")
    @BeanMapping(ignoreByDefault = true)
    FaultDto toFaultDto(Fault entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "votes", target = "votes")
    @BeanMapping(ignoreByDefault = true)
    BuildingDto toDtoWithVotes(Building entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "dateFinish", target = "dateFinish")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "upVote", target = "upVote")
    @Mapping(source = "downVote", target = "downVote")
    @Mapping(source = "finished", target = "finished")
    @BeanMapping(ignoreByDefault = true)
    VoteDto toVoteDto(Vote entity);
}
