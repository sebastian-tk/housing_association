package com.app.housing_association.building.service;

import com.app.housing_association.building.entity.Building;
import com.app.housing_association.common.service.abstracts.CrudService;

import java.util.Optional;

public interface BuildingService extends CrudService<Building, Long> {

    Optional<Building> findByIdWithNotices(Long id, Boolean archived);

    Optional<Building> findByIdWithFaults(Long id, Boolean fixed);

    Optional<Building> findByIdWithVotes(Long id, Boolean finished);
}
