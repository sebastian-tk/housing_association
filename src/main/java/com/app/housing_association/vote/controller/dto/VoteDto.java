package com.app.housing_association.vote.controller.dto;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.user.controller.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteDto extends BaseDto<Long> {

    private String title;
    private String description;
    private Integer upVote;
    private Integer downVote;
    private LocalDate dateFinish;
    private Boolean finished;
    private Set<UserDto> users;
    private BuildingDto building;

}
