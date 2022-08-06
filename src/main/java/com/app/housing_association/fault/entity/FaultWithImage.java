package com.app.housing_association.fault.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FaultWithImage extends Fault{

    private byte[] image;

    public FaultWithImage(Fault fault) {
        setId(fault.getId());
        setTitle(fault.getTitle());
        setDescription(fault.getDescription());
        setBuilding(fault.getBuilding());
        setImagePath(fault.getImagePath());
        setFixed(fault.isFixed());
    }
}
