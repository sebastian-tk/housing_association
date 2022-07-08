package com.app.housing_association.rate.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.rate.entity.Rate;
import com.app.housing_association.rate.repository.RateRepository;
import org.springframework.stereotype.Service;

@Service
public class RateBasicService extends AbstractCrudService<Rate, Long> implements RateService {

    public RateBasicService(RateRepository buildingRepository) {
        super(buildingRepository);
    }
}
