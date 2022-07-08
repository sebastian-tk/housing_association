package com.app.housing_association.rate.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.rate.controller.dto.RateDto;
import com.app.housing_association.rate.controller.mapper.RateMapperImpl;
import com.app.housing_association.rate.entity.Rate;
import com.app.housing_association.rate.service.RateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class RateController extends GenericRestController<Rate, RateDto, Long> {

    public RateController(RateService service, RateMapperImpl mapper) {
        super(service, mapper);
    }
}
