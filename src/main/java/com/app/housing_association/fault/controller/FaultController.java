package com.app.housing_association.fault.controller;

import com.app.housing_association.fault.controller.dto.FaultBaseDto;
import com.app.housing_association.fault.controller.dto.FaultDto;
import com.app.housing_association.fault.controller.mapper.FaultMapper;
import com.app.housing_association.fault.service.FaultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/faults")
public class FaultController {

    private static final String DATA_POST_TYPE = "json";
    private static final String FILE_POST_TYPE = "image";

    private final FaultService service;
    private final FaultMapper mapper;

    public FaultController(FaultService faultService, FaultMapper faultMapper) {
        this.service = faultService;
        this.mapper = faultMapper;
    }

    /**
     * The method gets all U objects in List then returns object in ResponseEntity and mapping result
     * to URL as endpoint. Object is returns with code 200 ok
     *
     * @return ResponseEntity object with List which contains all U objects
     */
    @GetMapping()
    public ResponseEntity<List<FaultBaseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAll()
                        .stream()
                        .map(mapper::toBaseDtoForAll)
                        .collect(toList()));
    }

    @GetMapping("/fixed")
    public ResponseEntity<List<FaultBaseDto>> getAllWithFixed(@RequestParam Boolean fixed) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAllWithFixed(fixed)
                        .stream()
                        .map(mapper::toBaseDto)
                        .collect(toList()));
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
    public ResponseEntity<FaultDto> getById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(service::convertImageById)
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
    public ResponseEntity<FaultDto> create(@RequestPart(DATA_POST_TYPE) FaultDto input, @RequestPart(FILE_POST_TYPE) MultipartFile image) {
        return Optional.of(input)
                .map(mapper::toEntity)
                .map(fault -> service.saveWithImage(fault, image))
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
    public ResponseEntity<FaultDto> update(@PathVariable Long id, @RequestPart(DATA_POST_TYPE) FaultDto input,
                                           @RequestPart(FILE_POST_TYPE) MultipartFile image) {
        input.setId(id);
        return service
                .findById(id)
                .map(entity -> mapper.toEntity(input))
                .flatMap(fault -> service.updateWithImage(fault, image))
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/fixed")
    public ResponseEntity<Void> updateFixed(@PathVariable Long id, @RequestParam Boolean fixed) {
        return service
                .findById(id)
                .flatMap(fault -> service.updateFixed(fault, fixed))
                .map(mapper::toBaseDto)
                .map(dto -> new ResponseEntity<Void>(HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
