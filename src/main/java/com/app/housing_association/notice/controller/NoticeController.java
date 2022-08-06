package com.app.housing_association.notice.controller;

import com.app.housing_association.notice.controller.dto.NoticeDto;
import com.app.housing_association.notice.controller.mapper.NoticeMapper;
import com.app.housing_association.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/notices")
public class NoticeController {
    
    private final NoticeService service;
    private final NoticeMapper mapper;

    public NoticeController(NoticeService noticeService, NoticeMapper noticeMapper) {
        this.service = noticeService;
        this.mapper = noticeMapper;
    }

    @GetMapping()
    public ResponseEntity<List<NoticeDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAll()
                        .stream()
                        .map(mapper::toBaseDto)
                        .collect(toList()));
    }

    @GetMapping("/archived")
    public ResponseEntity<List<NoticeDto>> getAllWithFixed(@RequestParam Boolean archived) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAllByArchived(archived)
                        .stream()
                        .map(mapper::toBaseDto)
                        .collect(toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDto> getById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<NoticeDto> create(@RequestBody NoticeDto input) {
        return Optional.of(input)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseThrow();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeDto> update(@PathVariable Long id, @RequestBody NoticeDto input) {
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
