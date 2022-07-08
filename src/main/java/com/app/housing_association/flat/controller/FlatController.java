package com.app.housing_association.flat.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.flat.controller.dto.FlatDto;
import com.app.housing_association.flat.controller.mapper.FlatMapperImpl;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.flat.service.FlatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flats")
public class FlatController extends GenericRestController<Flat, FlatDto, Long> {

    public FlatController(FlatService service, FlatMapperImpl mapper) {
        super(service, mapper);
    }
}
