package com.app.housing_association.user.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.user.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

    @NotBlank(message = "{user.name.blank}")
    private String name;

    @Email(regexp = "user.email.validation.regexp",flags = CASE_INSENSITIVE )
    @NotBlank(message = "{user.email.blank}")
    private String email;

    @NotBlank
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;
}
