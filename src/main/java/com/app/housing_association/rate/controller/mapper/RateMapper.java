package com.app.housing_association.rate.controller.mapper;

import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.rate.controller.dto.RateDto;
import com.app.housing_association.rate.entity.Rate;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;

@Mapper(componentModel = "spring", imports = Collections.class)
public interface RateMapper extends GenericMapper<Rate, RateDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "heatingM2", target = "heatingM2")
    @Mapping(source = "renovationFundM2", target = "renovationFundM2")
    @Mapping(source = "rentRentM2", target = "rentRentM2")
    @Mapping(source = "rentPropertyM2", target = "rentPropertyM2")
    @Mapping(source = "exploitationM2", target = "exploitationM2")
    @Mapping(source = "administrationM2", target = "administrationM2")
    @Mapping(source = "warmWaterPer", target = "warmWaterPer")
    @Mapping(source = "coldWaterPer", target = "coldWaterPer")
    @Mapping(source = "sewagePer", target = "sewagePer")
    @Mapping(source = "rubbishPer", target = "rubbishPer")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Rate toEntity(RateDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    RateDto toDto(Rate entity);
}
