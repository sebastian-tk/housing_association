package com.app.housing_association.building.controller;

import com.app.housing_association.building.controller.dto.BuildingDto;
import com.app.housing_association.building.controller.mapper.BuildingMapper;
import com.app.housing_association.building.entity.Building;
import com.app.housing_association.building.service.BuildingService;
import com.app.housing_association.common.controller.GenericRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buildings")
public class BuildingController extends GenericRestController<Building, BuildingDto, Long> {

    private final BuildingService service;
    private final BuildingMapper mapper;

    public BuildingController(BuildingService service, BuildingMapper mapper) {
        super(service, mapper);
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}/notices")
    public ResponseEntity<BuildingDto> getWithNotices(@PathVariable Long id, @RequestParam(required = false) Boolean archived) {
        return service
                .findByIdWithNotices(id, archived)
                .map(mapper::toDtoWithNotices)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/faults")
    public ResponseEntity<BuildingDto> getWithFaults(@PathVariable Long id, @RequestParam(required = false) Boolean fixed) {
        return service
                .findByIdWithFaults(id, fixed)
                .map(mapper::toDtoWithFaults)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/votes")
    public ResponseEntity<BuildingDto> getWithVotes(@PathVariable Long id, @RequestParam(required = false) Boolean finished) {
        return service
                .findByIdWithVotes(id, finished)
                .map(mapper::toDtoWithVotes)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
