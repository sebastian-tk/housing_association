package com.app.housing_association.flat.service;

import com.app.housing_association.common.service.CrudService;
import com.app.housing_association.flat.entity.Flat;

public interface FlatService extends CrudService<Flat, Long> {

    boolean hasContract(Flat flat);
}
