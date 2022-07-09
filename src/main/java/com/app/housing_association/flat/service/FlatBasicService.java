package com.app.housing_association.flat.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.flat.repository.FlatRepository;
import org.springframework.stereotype.Service;

@Service
public class FlatBasicService extends AbstractCrudService<Flat, Long> implements FlatService {

    public FlatBasicService(FlatRepository flatRepository) {
        super(flatRepository);
    }
}
