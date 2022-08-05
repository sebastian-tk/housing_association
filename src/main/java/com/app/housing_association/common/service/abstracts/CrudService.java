package com.app.housing_association.common.service.abstracts;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    Optional<T> findById(ID id);

    T save(T input);

    Optional<T> update(T input);

    List<T> findAll();

    void delete(ID id);
}
