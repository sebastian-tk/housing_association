package com.app.housing_association.fee.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.contract.entity.Contract;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fees")
public class Fee extends BaseEntity<Long> {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "heating", precision = 5, scale = 2)
    private BigDecimal heating;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "renovation_found", precision = 5, scale = 2)
    private BigDecimal renovationFund;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(precision = 5, scale = 2)
    private BigDecimal rent;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(precision = 5, scale = 2)
    private BigDecimal exploitation;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(precision = 5, scale = 2)
    private BigDecimal administration;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "warm_water", precision = 5, scale = 2)
    private BigDecimal warmWater;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "cold_water", precision = 5, scale = 2)
    private BigDecimal coldWater;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(precision = 5, scale = 2)
    private BigDecimal sewage;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(precision = 5, scale = 2)
    private BigDecimal rubbish;

    @OneToOne(mappedBy = "fee")
    private Contract contract;
}