package com.app.housing_association.flat.controller;

import com.app.housing_association.flat.controller.dto.FlatContractDto;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.controller.mapper.FlatMapper;
import com.app.housing_association.flat.controller.mapper.FlatMapperImpl;
import com.app.housing_association.flat.service.FlatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/flats")
public class FlatController{

    private final FlatService service;
    private final FlatMapper mapper;

    public FlatController(FlatService service, FlatMapperImpl mapper) {
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

    /**
     * The method gets all U objects in List then returns object in ResponseEntity and mapping result
     * to URL as endpoint. Object is returns with code 200 ok
     *
     * @return ResponseEntity object with List which contains all U objects
     */
    @GetMapping()
    public ResponseEntity<List<FlatDto>> getAll(@RequestParam(defaultValue = "false", required = false) boolean available) {
         return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAll()
                        .stream()
                        .map(mapper::toDto)
                        .collect(toList()));
    }

    @GetMapping("/available")
    public ResponseEntity<List<FlatDto>> getAvailable() {
        return service
                .findByAvailableIsTrue()
                .map(flats -> flats
                        .stream()
                        .map(mapper::toDto)
                        .toList())
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * The method takes the id from the URL as a parameter and finds the entity of U object with the
     * required id and then maps the entity to U object and returns U object with code 200 ok. If
     * object not exist returns code 404 not found
     *
     * @param id object ID
     * @return ResponseEntity with U object with id equals input id
     */
    @GetMapping("/{id}")
    public ResponseEntity<FlatDto> getById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Method takes U object from endpoint in URL then maps to entity and adds new entity. Next
     * returns added entity as U object after mapping with code 200 ok
     *
     * @param input object U
     * @return ResponseEntity with U object which was added
     */
    @PostMapping()
    public ResponseEntity<FlatDto> create(@RequestBody FlatDto input) {
        return Optional.of(input)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseThrow();
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
    public ResponseEntity<FlatDto> update(@PathVariable Long id, @RequestBody FlatDto input) {
        input.setId(id);
        return service
                .findById(id)
                .map(entity -> mapper.toEntity(input))
                .flatMap(service::update)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Method takes id of U object to remove from URL as parameter then removes object with require id
     * and returns code 204 no content. If object with require id not exist returns code 404 not found
     *
     * @param id ID object
     * @return ResponseEntity with type void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(
                        entity -> {
                            service.delete(id);
                            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                        })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
