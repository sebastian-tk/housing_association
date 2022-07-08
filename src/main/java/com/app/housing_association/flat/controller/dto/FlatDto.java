package com.app.housing_association.flat.controller.dto;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.flat.entity.enums.FlatType;
import com.app.housing_association.flat.entity.enums.TypeUse;
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
public class FlatDto extends BaseDto<Long> {

    private Integer storey;
    private Integer number;
    private FlatType type;
    private Integer areaM2;
    private TypeUse typeUse;
    private BuildingDto building;
}
