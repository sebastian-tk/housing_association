package com.app.housing_association.flat.entity;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.contract.entity.Contract;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flats")
public class Flat extends BaseEntity<Long> {

    @NotNull
    @Min(-1)
    @Max(10)
    private Integer storey;

    @NotNull
    private Integer number;

    @NotNull
    @Min(1)
    @Column(name = "nr_staircase")
    private Integer nrStaircase;

    @NotNull
    @Min(1)
    @Column(name = "area_m2")
    private Integer areaM2;

    private Boolean available = true;

    @ManyToOne
    private Building building;

    @OneToOne(mappedBy = "flat")
    private Contract contract;
}
