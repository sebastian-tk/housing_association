package com.app.housing_association.fault.entity;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.app.housing_association.common.utils.IValidation.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "faults")
public class Fault extends BaseEntity<Long> {

    @NotBlank(message = FAULT_TITLE_VALIDATION)
    private String title;

    @NotBlank(message = FAULT_DESCRIPTION_VALIDATION)
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    private boolean fixed;

    @NotNull(message = FAULT_BUILDING_VALIDATION)
    @ManyToOne
    private Building building;

}
