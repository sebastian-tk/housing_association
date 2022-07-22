package com.app.housing_association.building.service;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.repository.BuildingRepository;
import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.flat.entity.Flat;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparingInt;

@Service
public class BuildingBasicService extends AbstractCrudService<Building, Long> implements BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingBasicService(BuildingRepository buildingRepository) {
        super(buildingRepository);
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> findAll() {
        return   buildingRepository
                .findAll()
                .stream()
                .map(this::sortFlats)
                .toList();
    }

    private Building sortFlats(Building building){
        var sortedFlats = building
                .getFlats()
                .stream()
                .sorted(comparingInt(Flat::getNrStaircase)
                        .thenComparing(Flat::getNumber))
                .toList();
        building.setFlats(sortedFlats);
        return building;
    }
}
