package com.app.housing_association.flat.controller.dto;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.contract.controller.dto.ContractDto;
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
public class FlatContractDto extends BaseDto<Long> {

    private Integer storey;
    private Integer number;
    private Integer nrStaircase;
    private Integer areaM2;
    private Boolean available;
    private BuildingDto building;
    private ContractDto contract;
}
