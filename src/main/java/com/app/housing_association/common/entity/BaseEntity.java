package com.app.housing_association.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue
    @Column(unique = true,nullable = false)
    private ID id;
}
