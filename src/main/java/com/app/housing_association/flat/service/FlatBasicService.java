package com.app.housing_association.flat.service;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.service.BuildingService;
import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.flat.repository.FlatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.app.housing_association.common.utils.IValidation.*;
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
            throw new IllegalArgumentException(FLAT_NULL_VALIDATION);
        }
        return flatRepository
                .findById(flat.getId())
                .map(foundFlat -> nonNull(foundFlat.getContract()))
                .orElse(false);
    }

    @Override
    public Integer getAreaById(Long id) {
        if (isNull(id)) {
            throw new IllegalArgumentException(FLAT_ID_NULL);
        }
        return flatRepository
                .findById(id)
                .map(Flat::getAreaM2)
                .orElseThrow(()-> new IllegalArgumentException(FLAT_NOT_EXIST+": "+id));
    }

    @Override
    public Flat save(Flat inputFlat) {
        if (isNull(inputFlat)) {
            throw new IllegalArgumentException(FLAT_NULL_VALIDATION);
        }
        validateDataFlat(inputFlat);
        return flatRepository.save(inputFlat);
    }

    @Override
    public Optional<Flat> updateAvailable(Long id, boolean available) {
        if (isNull(id)) {
            throw new IllegalArgumentException(FLAT_ID_NULL);
        }
        return flatRepository
                .findById(id)
                .map(flat -> {
                    flat.setAvailable(available);
                    return flat;
                })
                .map(this::save);
    }

    @Override
    public Optional<List<Flat>> findByAvailableIsTrue() {
         var flats = flatRepository.findByAvailableIsTrue();
         return flats.isEmpty() ? Optional.empty() : Optional.of(flats);
    }

    private void validateDataFlat(Flat input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException(FLAT_DATA_NULL);
        }
        if (Objects.isNull(input.getBuilding())) {
            throw new IllegalArgumentException(FLAT_BUILDING_NULL);
        }
        Long buildingId = input.getBuilding().getId();
        if (Objects.isNull(buildingId)) {
            throw new IllegalArgumentException(FLAT_ID_BUILDING_NULL);
        }
        var foundBuilding = buildingService.findById(buildingId);
        if (foundBuilding.isEmpty()) {
            throw new IllegalArgumentException(FLAT_ID_BUILDING_NOT_EXIST);
        }
        if (canFlatBeAddedToBuilding(foundBuilding.get(), input)) {
            throw new IllegalArgumentException(FLAT_CAN_NOT_BE_ADD_TO_BUILDING);
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

