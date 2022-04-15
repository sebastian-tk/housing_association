package com.app.housing_association.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RestController<U, ID> {

    ResponseEntity<List<U>> getAll();

    ResponseEntity<U> getById(@PathVariable ID id);

    ResponseEntity<U> create(@RequestBody U input);

    ResponseEntity<U> update(@PathVariable ID id, @RequestBody U input);

    ResponseEntity<Void> removeById(@PathVariable ID id);
}
