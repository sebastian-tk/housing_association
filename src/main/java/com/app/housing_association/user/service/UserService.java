package com.app.housing_association.user.service;

import com.app.housing_association.common.service.CrudService;
import com.app.housing_association.user.entity.User;

public interface UserService extends CrudService<User, Long> {

    boolean canNotHaveContract(User user);

}
