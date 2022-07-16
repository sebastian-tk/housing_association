package com.app.housing_association.flat.service;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.service.BuildingService;
import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.flat.repository.FlatRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class FlatBasicService extends AbstractCrudService<Flat, Long> implements FlatService {

    private final FlatRepository flatRepository;
    private final BuildingService buildingService;


    public FlatBasicService(FlatRepository flatRepository, BuildingService buildingService) {
        super(flatRepository);
        this.flatRepository = flatRepository;
        this.buildingService = buildingService;
    }

    @Override
    public boolean hasContract(Flat flat) {
        if (isNull(flat)) {
            throw new IllegalArgumentException("Flat cannot be null");
        }
        return flatRepository
                .findById(flat.getId())
                .map(foundFlat -> nonNull(foundFlat.getContract()))
                .orElse(false);
    }

    @Override
    public Flat save(Flat inputFlat) {
        validateDataFlat(inputFlat);
        return super.save(inputFlat);
    }

    private void validateDataFlat(Flat input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("Data input Flat cannot be null");
        }
        if (Objects.isNull(input.getBuilding())) {
            throw new IllegalArgumentException("Flat building cannot be null");
        }
        Long buildingId = input.getBuilding().getId();
        if (Objects.isNull(buildingId)) {
            throw new IllegalArgumentException("Building id from flat cannot be null");
        }
        var foundBuilding = buildingService.findById(buildingId);
        if (foundBuilding.isEmpty()) {
            throw new IllegalArgumentException("Building with provide id not exist");
        }
        if (canFlatBeAddedToBuilding(foundBuilding.get(), input)) {
            throw new IllegalArgumentException("Due to constructional reasons, the apartment cannot be added to the " +
                    "building or the place is already occupied");
        }
    }

    private boolean canFlatBeAddedToBuilding(Building building, Flat flat) {
        if (flat.getNrStaircase() > building.getStaircase()) {
            if (flat.getStorey() > building.getNumberStoreys()) {
                return building
                        .getFlats()
                        .stream()
                        .filter(f -> f.getNrStaircase().equals(flat.getNrStaircase()))
                        .filter(f -> f.getStorey().equals(flat.getStorey()))
                        .noneMatch(f -> f.getNumber().equals(flat.getNumber()));
            }
        }
        return false;
    }
}

