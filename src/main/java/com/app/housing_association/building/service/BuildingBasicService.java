package com.app.housing_association.building.service;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.repository.BuildingRepository;
import com.app.housing_association.common.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class BuildingBasicService extends AbstractCrudService<Building, Long> implements BuildingService {

    public BuildingBasicService(BuildingRepository buildingRepository) {
        super(buildingRepository);
    }
}
