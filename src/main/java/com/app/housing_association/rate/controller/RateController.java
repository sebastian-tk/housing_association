package com.app.housing_association.rate.controller;

import com.app.housing_association.rate.controller.dto.RateDto;
import com.app.housing_association.rate.controller.mapper.RateMapper;
import com.app.housing_association.rate.service.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rates")
public class RateController{

    private final RateService service;
    private final RateMapper mapper;

    public RateController(RateService service, RateMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * The method gets all U objects in List then returns object in ResponseEntity and mapping result
     * to URL as endpoint. Object is returns with code 200 ok
     *
     * @return ResponseEntity object with List which contains all U objects
     */
    @GetMapping()
    public ResponseEntity<List<RateDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAll()
                        .stream()
                        .map(mapper::toDto)
                        .collect(toList()));
    }

    /**
     * Method takes id from URl as parameter and data entity U to update. Next updates data existing U
     * object with input data and returns ResponseEntity with updated U object and code 200 ok. If U
     * object with require id not exist returns status 404 not found
     *
     * @param id    object ID as id
     * @param input object U to put
     * @return ResponseEntity with updated U object
     */
    @PutMapping("/{id}")
    public ResponseEntity<RateDto> update(@PathVariable Long id, @RequestBody RateDto input) {
        input.setId(id);
        return service
                .findById(id)
                .map(entity -> mapper.toEntity(input))
                .flatMap(service::update)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
