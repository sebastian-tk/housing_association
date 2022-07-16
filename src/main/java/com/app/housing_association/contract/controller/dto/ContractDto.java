package com.app.housing_association.contract.controller.dto;

import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.contract.entity.enums.ContractType;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.user.controller.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDto extends BaseDto<Long> {

    private ContractType type;
    private Integer amountPeople;
    private Instant startTime;
    private Instant finishTime;
    private UserDto user;
    private FlatDto flat;
    private FeeDto fee;
}
