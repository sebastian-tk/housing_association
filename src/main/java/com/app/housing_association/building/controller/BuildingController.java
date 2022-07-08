package com.app.housing_association.building.controller;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.controller.mapper.BuildingMapperImpl;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.service.BuildingService;
import com.app.housing_association.common.controller.GenericRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class BuildingController extends GenericRestController<Building, BuildingDto, Long> {

    public BuildingController(BuildingService service, BuildingMapperImpl mapper) {
        super(service, mapper);
    }
}
