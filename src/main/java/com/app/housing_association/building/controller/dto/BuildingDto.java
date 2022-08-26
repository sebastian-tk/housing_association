package com.app.housing_association.building.controller.dto;

import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.fault.controller.dto.FaultDto;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.notice.controller.dto.NoticeDto;
import com.app.housing_association.vote.controller.dto.VoteDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildingDto extends BaseDto<Long> {

    private String name;
    private String town;
    private String street;
    private String number;
    private String zipCode;
    private Integer yearConstruction;
    private Integer areaM2;
    private Integer numberStoreys;
    private Integer flatsPerStorey;
    private Integer staircase;
    private List<FlatDto> flats;
    private Set<NoticeDto> notices;
    private Set<VoteDto> votes;
    private Set<FaultDto> faults;
}
