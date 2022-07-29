package com.app.housing_association.flat.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.flat.controller.dto.FlatContractDto;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.controller.mapper.FlatMapper;
import com.app.housing_association.flat.controller.mapper.FlatMapperImpl;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.flat.service.FlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flats")
public class FlatController extends GenericRestController<Flat, FlatDto, Long> {

    private final FlatService service;
    private final FlatMapper mapper;

    public FlatController(FlatService service, FlatMapperImpl mapper) {
        super(service, mapper);
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}/contract")
    public ResponseEntity<FlatContractDto> getByIdWithContract(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toFlatContractDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
