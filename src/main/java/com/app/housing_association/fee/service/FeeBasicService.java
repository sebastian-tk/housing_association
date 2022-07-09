package com.app.housing_association.fee.service;

import com.app.housing_association.common.service.AbstractCrudService;
import com.app.housing_association.contract.entity.enums.ContractType;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.fee.entity.model.DataForCalculationFee;
import com.app.housing_association.fee.repository.FeeRepository;
import com.app.housing_association.rate.entity.Rate;
import com.app.housing_association.rate.service.RateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.app.housing_association.contract.entity.enums.ContractType.LEASE_AGREEMENT;
import static java.util.Objects.isNull;

@Service
public class FeeBasicService extends AbstractCrudService<Fee, Long> implements FeeService {

    private final static int CALCULATION_SCALE = 2;
    private final static RoundingMode CALCULATION_ROUNDING_MODE = RoundingMode.FLOOR;


    private final FeeRepository feeRepository;
    private final RateService rateService;

    public FeeBasicService(FeeRepository feeRepository, RateService rateService) {
        super(feeRepository);
        this.feeRepository = feeRepository;
        this.rateService = rateService;
    }

    @Override
    public Fee calculateAndSaveFeeByData(DataForCalculationFee data) {
        if (isNull(data)) {
            throw new IllegalArgumentException("Data for calculation fee can not be null");
        }
        return feeRepository.save(calculateFeesByData(data));
    }

    private Fee calculateFeesByData(DataForCalculationFee data) {
        Rate rates = getRates();
        ContractType type = data.getType();
        BigDecimal m2 = BigDecimal.valueOf(data.getFlatAreaM2());
        BigDecimal amountPeople = BigDecimal.valueOf(data.getAmountPerson());
        Fee fee = new Fee();
        fee.setHeating(rates.getHeatingM2().multiply(m2).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setRenovationFund(rates.getRenovationFundM2().multiply(m2).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setRent(calculateRent(m2, rates.getRentRentM2(), rates.getRentPropertyM2(), type));
        fee.setExploitation(rates.getExploitationM2().multiply(m2).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setAdministration(rates.getAdministrationM2().multiply(m2).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setWarmWater(rates.getWarmWaterPer().multiply(amountPeople).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setColdWater(rates.getColdWaterPer().multiply(amountPeople).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setSewage(rates.getSewagePer().multiply(amountPeople).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        fee.setRubbish(rates.getRubbishPer().multiply(amountPeople).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE));
        return fee;
    }

    private Rate getRates() {
        return rateService
                .getFirstRate()
                .orElseThrow(() -> new IllegalStateException("Could not get Rate factors"));
    }

    private BigDecimal calculateRent(BigDecimal m2, BigDecimal rentRentFactor, BigDecimal rentPropertyFactor, ContractType type) {
        return type == LEASE_AGREEMENT ?
                m2.multiply(rentRentFactor).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE)
                : m2.multiply(rentPropertyFactor).setScale(CALCULATION_SCALE, CALCULATION_ROUNDING_MODE);
    }
}
