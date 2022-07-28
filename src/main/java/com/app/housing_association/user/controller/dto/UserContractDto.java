package com.app.housing_association.user.controller.dto;

import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.user.entity.enums.Role;
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
public class UserContractDto extends BaseDto<Long> {

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;
    private Role role;
    private ContractDto contract;
}
