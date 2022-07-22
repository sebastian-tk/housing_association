package com.app.housing_association.building.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.flat.entity.Flat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

import static com.app.housing_association.common.utils.IValidation.BUILDING_NUMBER_VALIDATION_REGEXP;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building extends BaseEntity<Long> {

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String town;

    @NotBlank
    private String street;

    @NotNull
    @Pattern(regexp =BUILDING_NUMBER_VALIDATION_REGEXP)
    private String number;

    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Column(name = "year_construction")
    private Integer yearConstruction;

    @NotNull
    @Min(1)
    @Column(name = "area_m2")
    private Integer areaM2;

    @NotNull
    @Min(-1)
    @Max(10)
    @Column(name = "number_storeys")
    private Integer numberStoreys;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "flats_per_storey")
    private Integer flatsPerStorey;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer staircase;

    @OneToMany(
            mappedBy = "building",
            cascade = CascadeType.ALL
    )
    private List<Flat> flats = new ArrayList<>();

    public void setFlats(final List<Flat> flats) {
        this.flats.clear();
        flats.forEach(flat -> {
            if(!this.flats.contains(flat)){
                flat.setBuilding(this);
                this.flats.add(flat);
            }
        });
    }
}
