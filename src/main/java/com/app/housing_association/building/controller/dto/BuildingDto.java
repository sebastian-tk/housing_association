package com.app.housing_association.building.controller.dto;

import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildingDto extends BaseDto<Long> {

    private String name;
    private String town;
    private String street;
    private String number;
    private String zipCode;
    private Integer yearConstruction;
    private Integer areaM2;
    private Integer numberStoreys;
    private Integer flatsPerStorey;
    private Integer staircase;
    private List<FlatDto> flats;
}
