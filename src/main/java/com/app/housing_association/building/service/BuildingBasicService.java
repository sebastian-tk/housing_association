package com.app.housing_association.building.service;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.repository.BuildingRepository;
import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.flat.entity.Flat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.app.housing_association.common.utils.IValidation.BUILDING_ID_NULL_ERROR;
import static java.util.Comparator.comparingInt;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;

@Service
public class BuildingBasicService extends AbstractCrudService<Building, Long> implements BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingBasicService(BuildingRepository buildingRepository) {
        super(buildingRepository);
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository
                .findAll()
                .stream()
                .map(this::sortFlats)
                .toList();
    }

    private Building sortFlats(Building building) {
        var sortedFlats = building
                .getFlats()
                .stream()
                .sorted(comparingInt(Flat::getNrStaircase)
                        .thenComparing(Flat::getNumber))
                .toList();
        building.setFlats(sortedFlats);
        return building;
    }

    @Override
    public Optional<Building> findByIdWithNotices(Long id, Boolean noticesStatus) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException(BUILDING_ID_NULL_ERROR);
        }
        var foundedBuilding = buildingRepository.findById(id);
        return isNull(noticesStatus)
                ? foundedBuilding
                : foundedBuilding.map(building -> getNoticesWithArchived(building, noticesStatus));
    }

    @Override
    public Optional<Building> findByIdWithFaults(Long id, Boolean fixed) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException(BUILDING_ID_NULL_ERROR);
        }
        var foundedBuilding = buildingRepository.findById(id);
        return isNull(fixed)
                ? foundedBuilding
                : foundedBuilding.map(building -> getFaultsWithFixed(building, fixed));
    }

    @Override
    public Optional<Building> findByIdWithVotes(Long id, Boolean finished) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException(BUILDING_ID_NULL_ERROR);
        }
        var foundedBuilding = buildingRepository.findById(id);
        return isNull(finished)
                ? foundedBuilding
                : foundedBuilding.map(building -> getVotesWithFinished(building, finished));
    }

    private Building getNoticesWithArchived(Building building, boolean archived) {
        var filteredNotices = building
                .getNotices()
                .stream()
                .filter(notice -> notice.isArchived() == archived)
                .collect(toSet());
        building.setNotices(filteredNotices);
        return building;
    }

    private Building getFaultsWithFixed(Building building, boolean fixed) {
        var filteredFaults = building
                .getFaults()
                .stream()
                .filter(fault -> fault.isFixed() == fixed)
                .collect(toSet());
        building.setFaults(filteredFaults);
        return building;
    }

    private Building getVotesWithFinished(Building building, boolean finished) {
        var filteredVotes = building
                .getVotes()
                .stream()
                .filter(vote -> vote.getFinished() == finished)
                .collect(toSet());
        building.setVotes(filteredVotes);
        return building;
    }
}
