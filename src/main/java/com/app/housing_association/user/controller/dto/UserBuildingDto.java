package com.app.housing_association.user.controller.dto;

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
public class UserBuildingDto extends BaseDto<Long> {

    private String username;
    private Long buildingId;
}
