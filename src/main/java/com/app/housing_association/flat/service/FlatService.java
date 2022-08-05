package com.app.housing_association.flat.service;

import com.app.housing_association.common.service.abstracts.CrudService;
import com.app.housing_association.flat.entity.Flat;

import java.util.List;
import java.util.Optional;

public interface FlatService extends CrudService<Flat, Long> {

    boolean hasContract(Flat flat);

    Integer getAreaById(Long id);

    Optional<List<Flat>> findByAvailableIsTrue();
}
