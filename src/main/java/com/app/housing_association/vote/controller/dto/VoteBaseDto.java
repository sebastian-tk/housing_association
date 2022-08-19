package com.app.housing_association.vote.controller.dto;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.common.model.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteBaseDto extends BaseDto<Long> {

    private String title;
    private Integer upVote;
    private Integer downVote;
    private Boolean finished;
    private BuildingDto building;
}
