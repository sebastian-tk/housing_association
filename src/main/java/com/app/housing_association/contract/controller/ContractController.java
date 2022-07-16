package com.app.housing_association.contract.controller;

import com.app.housing_association.common.controller.GenericRestController;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.controller.mapper.ContractMapper;
import com.app.housing_association.contract.controller.mapper.ContractMapperImpl;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.contract.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController extends GenericRestController<Contract, ContractDto, Long> {

    public ContractController(ContractService service, ContractMapperImpl mapper) {
        super(service, mapper);
    }
}
