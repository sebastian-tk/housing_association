package com.app.housing_association.user.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.common.utils.IValidation;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.user.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.app.housing_association.common.utils.IValidation.*;
import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

    @NotBlank(message = USER_USERNAME_BLANK)
    private String username;

    @Email(regexp = USER_EMAIL_VALIDATION_REGEXP, flags = CASE_INSENSITIVE, message = USER_EMAIL_SYNTAX_ERROR)
    @NotBlank(message = USER_EMAIL_BLANK)
    private String email;

    @NotEmpty(message = USER_PASSWORD_EMPTY)
    private String password;

    @NotNull()
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Contract contract;

}
