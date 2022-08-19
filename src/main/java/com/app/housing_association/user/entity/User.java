package com.app.housing_association.user.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.user.entity.enums.Role;
import com.app.housing_association.vote.entity.Vote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import static com.app.housing_association.common.utils.IValidation.*;
import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

    @NotBlank(message = USER_FIRSTNAME_VALIDATION)
    private String firstname;

    @NotBlank(message = USER_LASTNAME_VALIDATION)
    private String lastname;

    @Pattern(regexp = PHONE_NUMBER_VALIDATION_REGEXP, message = USER_PHONE_SYNTAX_ERROR)
    @NotBlank(message = USER_PHONE_NUMBER_VALIDATION)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = USER_USERNAME_BLANK)
    private String username;

    @Email(regexp = USER_EMAIL_VALIDATION_REGEXP, flags = CASE_INSENSITIVE, message = USER_EMAIL_SYNTAX_ERROR)
    @NotBlank(message = USER_EMAIL_BLANK)
    private String email;

    @NotBlank(message = USER_PASSWORD_NULL_VALIDATION)
    private String password;

    @NotNull()
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private Contract contract;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "users_votes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id"))
    private Set<Vote> votes = new HashSet<>();

    public void setVotes(Set<Vote> votes) {
        this.votes.clear();
        votes.forEach(vote -> {
            this.votes.add(vote);
            vote.getUsers().add(this);
        });
    }

    @PreRemove
    public void removeVotes() {
        this.votes.forEach(vote -> vote
                .getUsers()
                .remove(this));
        this.votes.clear();
    }
}
