package com.app.housing_association.vote.controller;

import com.app.housing_association.vote.controller.dto.VoteBaseDto;
import com.app.housing_association.vote.controller.dto.VoteDto;
import com.app.housing_association.vote.controller.mapper.VoteMapper;
import com.app.housing_association.vote.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private static final String DATA_POST_TYPE = "json";
    private static final String FILE_POST_TYPE = "pdf";

    private final VoteService service;
    private final VoteMapper mapper;

    public VoteController(VoteService voteService, VoteMapper voteMapper) {
        this.service = voteService;
        this.mapper = voteMapper;
    }

    @GetMapping()
    public ResponseEntity<List<VoteBaseDto>> getAllWithFinishedStatus(@RequestParam(required = false) Boolean finished) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAllOrByFinished(finished)
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
    public ResponseEntity<VoteDto> getById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdfB(@PathVariable Long id) {
        return service
                .findById(id)
                .map(service::convertFileById)
                .map(bytes -> ResponseEntity.ok().body(bytes))
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
    public ResponseEntity<VoteDto> create(@RequestPart(DATA_POST_TYPE) VoteDto input, @RequestPart(FILE_POST_TYPE) MultipartFile pdf) {
        return Optional.of(input)
                .map(mapper::toEntity)
                .map(fault -> service.saveWithFile(fault, pdf))
                .map(mapper::toDtoWithBuilding)
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
    public ResponseEntity<VoteDto> update(@PathVariable Long id, @RequestPart(DATA_POST_TYPE) VoteDto input,
                                          @RequestPart(FILE_POST_TYPE) MultipartFile image) {
        input.setId(id);
        return service
                .findById(id)
                .map(entity -> mapper.toEntity(input))
                .flatMap(fault -> service.updateWithFile(fault, image))
                .map(mapper::putToDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> update(@PathVariable Long id, @PathVariable Long userId, @RequestParam boolean vote) {
        return service
                .findById(id)
                .flatMap(entity -> service.addUserVote(entity, userId, vote))
                .map(mapper::putToDto)
                .map(dto -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
