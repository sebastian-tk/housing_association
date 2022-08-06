package com.app.housing_association.notice.entity;

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
import java.time.LocalDate;

import static com.app.housing_association.common.utils.IValidation.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notices")
public class Notice extends BaseEntity<Long> {

    @NotBlank(message = NOTICE_TITLE_NULL)
    private String title;

    @NotBlank(message = NOTICE_DESCRIPTION_NULL)
    private String description;

    private boolean archived;

    @NotNull
    @Column(name = "execution_date")
    private LocalDate executionDate;

    @NotNull(message = FAULT_BUILDING_VALIDATION)
    @ManyToOne
    private Building building;

}
