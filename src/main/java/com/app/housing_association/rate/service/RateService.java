package com.app.housing_association.rate.service;

import com.app.housing_association.common.service.CrudService;
import com.app.housing_association.rate.entity.Rate;

import java.util.Optional;

public interface RateService extends CrudService<Rate, Long> {
    Optional<Rate> getFirstRate();
}
