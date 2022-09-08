package com.app.housing_association.user.entity.model;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.user.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWithBuilding extends BaseEntity<Long> {
    private String username;
    private Long buildingId;

    public UserWithBuilding(User user, Long buildingId) {
        setId(user.getId());
        this.username = user.getUsername();
        this.buildingId = buildingId;
    }
}
