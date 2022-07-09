package com.app.housing_association.fee.controller.dto;

import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeDto extends BaseDto<Long> {

    private BigDecimal heating;
    private BigDecimal renovationFund;
    private BigDecimal rent;
    private BigDecimal serviceRent;
    private BigDecimal exploitation;
    private BigDecimal administration;
    private BigDecimal warmWater;
    private BigDecimal coldWater;
    private BigDecimal sewage;
    private BigDecimal rubbish;
    private ContractDto contract;
}
