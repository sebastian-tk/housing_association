package com.app.housing_association.user.controller;

import com.app.housing_association.user.controller.dto.UserContractDto;
import com.app.housing_association.user.controller.dto.UserDto;
import com.app.housing_association.user.controller.dto.UserWithChangingPasswordDto;
import com.app.housing_association.user.controller.mapper.UserMapper;
import com.app.housing_association.user.entity.enums.Role;
import com.app.housing_association.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.service = userService;
        this.mapper = userMapper;
    }

    @GetMapping("/{id}/contract")
    public ResponseEntity<UserContractDto> getByIdWithContract(@PathVariable Long id) {
        return service
                .findById(id)
                .map(mapper::toUserContractDto)
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
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(required = false) Role role) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findAllByRoleOrAll(role)
                        .stream()
                        .map(mapper::toDto)
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
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
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
    public ResponseEntity<UserDto> create(@RequestBody UserDto input) {
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
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto input) {
        input.setId(id);
        return service
                .findById(id)
                .map(entity -> mapper.toEntity(input))
                .flatMap(service::update)
                .map(mapper::toDto)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updateWithCheckedPassword(@PathVariable Long id, @RequestBody UserWithChangingPasswordDto input) {
        input.setId(id);
        return service
                .findById(id)
                .map(entity -> mapper.toEntityWithChangingPassword(input))
                .flatMap(service::updateWithChangePassword)
                .map(dto -> new ResponseEntity<Void>(HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
