package com.app.housing_association.contract.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.contract.repository.ContractRepository;
import com.app.housing_association.fee.entity.model.DataForCalculationFee;
import com.app.housing_association.fee.service.FeeService;
import org.springframework.stereotype.Service;

@Service
public class ContractBasicService extends AbstractCrudService<Contract, Long> implements ContractService {

    private final ContractRepository contractRepository;
    private final FeeService feeService;


    public ContractBasicService(ContractRepository contractRepository, FeeService feeService) {
        super(contractRepository);
        this.contractRepository = contractRepository;
        this.feeService = feeService;
    }

    @Override
    public Contract save(Contract contract) {
        //TODO check
        feeService.calculateAndSaveFeeByData(createDataForCalculationFee(contract));
        return contractRepository.save(contract);
    }

    private DataForCalculationFee createDataForCalculationFee(Contract contract) {
        return DataForCalculationFee
                .builder()
                .type(contract.getType())
                .flatAreaM2(contract.getFlat().getAreaM2())
                .amountPerson(contract.getAmountPeople())
                .build();
    }
}
