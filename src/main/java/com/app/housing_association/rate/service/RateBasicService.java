package com.app.housing_association.rate.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.rate.entity.Rate;
import com.app.housing_association.rate.repository.RateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateBasicService extends AbstractCrudService<Rate, Long> implements RateService {

    private final RateRepository rateRepository;

    public RateBasicService(RateRepository rateRepository) {
        super(rateRepository);
        this.rateRepository = rateRepository;
    }

    @Override
    public Optional<Rate> getFirstRate() {
        return Optional.of(rateRepository.findAll()).map(list -> list.get(0));
    }
}
