package com.app.housing_association.fee.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.controller.mapper.FeeMapperImpl;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.fee.service.FeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fees")
public class FeeController extends GenericRestController<Fee, FeeDto, Long> {

    public FeeController(FeeService service, FeeMapperImpl mapper) {
        super(service, mapper);
    }
}
