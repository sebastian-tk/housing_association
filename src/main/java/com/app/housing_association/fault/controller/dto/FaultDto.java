package com.app.housing_association.fault.controller.dto;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.common.model.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaultDto extends BaseDto<Long> {

    private String title;
    private String description;
    private String imagePath;
    private boolean fixed;
    private BuildingDto building;
}
