package com.app.housing_association.notice.controller.mapper;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.notice.controller.dto.NoticeDto;
import com.app.housing_association.notice.entity.Notice;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoticeMapper extends GenericMapper<Notice, NoticeDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "archived", target = "archived")
    @Mapping(source = "executionDate", target = "executionDate")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    @Override
    NoticeDto toDto(Notice entity);

    @InheritInverseConfiguration(name = "toDto")
    @Override
    Notice toEntity(NoticeDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "archived", target = "archived")
    @Mapping(source = "executionDate", target = "executionDate")
    @Mapping(source = "building", target = "building")
    @BeanMapping(ignoreByDefault = true)
    NoticeDto toBaseDto(Notice entity);

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

