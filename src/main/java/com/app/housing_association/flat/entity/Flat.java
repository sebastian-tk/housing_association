package com.app.housing_association.flat.entity;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.flat.entity.enums.FlatType;
import com.app.housing_association.flat.entity.enums.TypeUse;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flats")
public class Flat extends BaseEntity<Long> {

    @NotNull
    @Size(min = -1, max = 10)
    private Integer storey;

    @NotNull
    private Integer number;

    @NotNull
    private FlatType type;

    @NotNull
    @Column(name = "area_m2")
    private Integer areaM2;

    @Column(name = "type_use")
    private TypeUse typeUse;

    @ManyToOne
    private Building building;
}
