package com.app.housing_association.contract.service;

import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.contract.repository.ContractRepository;
import com.app.housing_association.fee.entity.model.DataForCalculationFee;
import com.app.housing_association.fee.service.FeeService;
import com.app.housing_association.flat.service.FlatService;
import com.app.housing_association.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.housing_association.common.utils.IValidation.*;
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
            throw new IllegalArgumentException(USER_NULL_VALIDATION);
        }
        if(isNull(contract.getFlat())){
            throw new IllegalArgumentException(FLAT_NULL_VALIDATION);
        }
        if(flatService.hasContract(contract.getFlat())){
            throw new IllegalArgumentException(CONTRACT_FLAT_VALIDATION);
        }
        if(userService.canNotHaveContract(contract.getUser())){
            throw new IllegalArgumentException(CONTRACT_USER_VALIDATION);
        }
        var savedUser = userService.save(contract.getUser());
        var savedFee = feeService.calculateAndSaveFeeByData(createDataForCalculationFee(contract));
        var savedFlat = flatService.updateAvailable(contract.getFlat().getId(),false).orElse(null);
        contract.setFee(savedFee);
        contract.setUser(savedUser);
        contract.setFlat(savedFlat);
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
        return flatService.getAreaById(flatId);
    }
}
