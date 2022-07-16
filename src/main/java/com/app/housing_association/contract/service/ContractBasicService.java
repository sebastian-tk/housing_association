package com.app.housing_association.contract.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.contract.repository.ContractRepository;
import com.app.housing_association.fee.entity.model.DataForCalculationFee;
import com.app.housing_association.fee.service.FeeService;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.flat.service.FlatService;
import com.app.housing_association.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
public class ContractBasicService extends AbstractCrudService<Contract, Long> implements ContractService {

    private final ContractRepository contractRepository;
    private final FeeService feeService;
    private final FlatService flatService;
    private final UserService userService;



    public ContractBasicService(ContractRepository contractRepository, FeeService feeService, FlatService flatService,
                                UserService userService) {
        super(contractRepository);
        this.contractRepository = contractRepository;
        this.feeService = feeService;
        this.flatService = flatService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Contract save(Contract contract) {
        if(isNull(contract.getUser()) ){
            throw new IllegalArgumentException("Contract must have user id");
        }
        System.out.println();
        if(isNull(contract.getFlat())){
            throw new IllegalArgumentException("Contract must have flat id");
        }
        if(flatService.hasContract(contract.getFlat())){
            throw new IllegalArgumentException("Flat has still active contract");
        }
        if(userService.canNotHaveContract(contract.getUser())){
            throw new IllegalArgumentException("User has still active contract");
        }
        var savedFee = feeService.calculateAndSaveFeeByData(createDataForCalculationFee(contract));
        contract.setFee(savedFee);
        return contractRepository.save(contract);
    }

    private DataForCalculationFee createDataForCalculationFee(Contract contract) {
        return DataForCalculationFee
                .builder()
                .type(contract.getType())
                .flatAreaM2(getFlat(contract
                        .getFlat()
                        .getId()))
                .amountPerson(contract.getAmountPeople())
                .build();
    }

    private Integer getFlat(Long flatId){
        return flatService
                .findById(flatId)
                .map(Flat::getAreaM2)
                .orElseThrow(()-> new IllegalArgumentException("Provide flat id:" + flatId+" not exist"));
    }
}
