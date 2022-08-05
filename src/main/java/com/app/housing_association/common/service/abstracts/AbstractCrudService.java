package com.app.housing_association.common.service.abstracts;


import com.app.housing_association.common.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T extends BaseEntity<ID>, ID extends Serializable> implements CrudService<T, ID> {

    protected final JpaRepository<T, ID> jpaRepository;

    /**
     * Method initializes AbstractCrudService with JpaRepository
     * @param jpaRepository object implementation JpaRepository
     */
    public AbstractCrudService(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Method finds object of type T with require id.If method finds object returns Optional with
     * object else empty Optional
     * @param id object of type ID
     * @return Optional with object of type T with require id, else empty Optional
     */
    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    /**
     * Method saves input object of type T and return this saved object with generated id.
     * If input null throw IllegalArgumentException
     * @param input object of type T
     * @return saved object T
     */
    @Override
    public T save(T input) {
        if (input == null) {
            throw new IllegalArgumentException("Object to save cannot be null");
        }
        input.setId(null);
        return jpaRepository.save(input);
    }

    /**
     * Method updates object in repository with data from input.If the object exists it will be
     * updated with data from input and method returns updated object in Optional. If object not
     * exist, returns empty Optional. If input null throw IllegalArgumentException
     * @param input object of type T
     * @return saved object T
     */
    @Override
    public Optional<T> update(T input) {
        if (input == null) {
            throw new IllegalArgumentException("Object to update cannot be null");
        }
        return jpaRepository
                .findById(input.getId())
                .map(obj -> jpaRepository.save(input));
    }

    /**
     * Method finds and returns List with all objects of type T or empty List if objects not exists
     * @return List with objects of type T,or empty List of objects not exist
     */
    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    /**
     * Method removes object of type T with require id.If id not exist throw
     * EmptyResultDataAccessException
     * @param id object ID
     */
    @Override
    public void delete(ID id) {
        jpaRepository.deleteById(id);
    }
}