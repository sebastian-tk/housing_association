package com.app.housing_association.rate.controller.dto;

import com.app.housing_association.common.model.BaseDto;
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
public class RateDto extends BaseDto<Long> {

    private BigDecimal heatingM2;
    private BigDecimal renovationFundM2;
    private BigDecimal flatRentM2;
    private BigDecimal serviceRentM2;
    private BigDecimal exploitationM2;
    private BigDecimal administrationM2;
    private BigDecimal warmWaterPer;
    private BigDecimal coldWaterPer;
    private BigDecimal sewagePer;
    private BigDecimal rubbishPer;
}
