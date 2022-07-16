package com.app.housing_association.rate.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.flat.entity.Flat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rates")
public class Rate extends BaseEntity<Long> {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "heating_m2", precision = 5,scale = 2)
    private BigDecimal heatingM2;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "renovation_found_m2", precision = 5,scale = 2)
    private BigDecimal renovationFundM2;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "rent_rent_m2", precision = 5,scale = 2)
    private BigDecimal rentRentM2;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "rent_property_m2", precision = 5,scale = 2)
    private BigDecimal rentPropertyM2;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "exploitation_m2", precision = 5,scale = 2)
    private BigDecimal exploitationM2;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "administration_m2", precision = 5,scale = 2)
    private BigDecimal administrationM2;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "warm_water_per", precision = 5,scale = 2)
    private BigDecimal warmWaterPer;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "cold_water_per", precision = 5,scale = 2)
    private BigDecimal coldWaterPer;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "sewage_per", precision = 5,scale = 2)
    private BigDecimal sewagePer;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "rubbish_per", precision = 5,scale = 2)
    private BigDecimal rubbishPer;
}