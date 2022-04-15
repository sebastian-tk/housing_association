package com.app.housing_association.common.controller.mapper;

import com.app.housing_association.common.model.BaseDto;
import com.app.housing_association.common.model.BaseEntity;

import java.io.Serializable;

public interface GenericMapper<T extends BaseEntity<ID>, U extends BaseDto<ID>, ID extends Serializable> {

    T toEntity(U dto);

    U toDto(T entity);
}
