package com.app.housing_association.vote.entity;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.app.housing_association.common.utils.IValidation.VOTE_DESCRIPTION_VALIDATION;
import static com.app.housing_association.common.utils.IValidation.VOTE_TITLE_VALIDATION;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity<Long> {

    @NotBlank(message = VOTE_TITLE_VALIDATION)
    private String title;

    @NotBlank(message = VOTE_DESCRIPTION_VALIDATION)
    private String description;

    @NotNull
    @Column(name = "up_vote")
    private Integer upVote = 0;

    @NotNull
    @Column(name = "down_vote")
    private Integer downVote = 0;

    @NotNull
    @Column(name = "date_finish")
    private LocalDate dateFinish;

    private Boolean finished=false;

    @Column(name = "file_path")
    private String filePath;

    @ManyToMany(mappedBy = "votes")
    private Set<User> users = new HashSet<>();

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "building_id")
    private Building building;

    public void setUsers(Set<User> users) {
        this.users.clear();
        users.forEach(user -> {
            this.users.add(user);
            user.getVotes().add(this);
        });
    }

    @PreRemove
    public void removeUsers() {
        this.users.forEach(user -> user
                .getVotes()
                .remove(this));
        this.users.clear();
    }

}
