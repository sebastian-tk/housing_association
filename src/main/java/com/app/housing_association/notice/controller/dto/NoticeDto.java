package com.app.housing_association.notice.controller.dto;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.common.model.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticeDto extends BaseDto<Long> {

    private String title;
    private String description;
    private boolean archived;
    private LocalDate executionDate;
    private BuildingDto building;
}
