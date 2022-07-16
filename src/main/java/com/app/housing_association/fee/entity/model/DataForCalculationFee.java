package com.app.housing_association.fee.entity.model;

import com.app.housing_association.contract.entity.enums.ContractType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataForCalculationFee {

    private Integer flatAreaM2;
    private ContractType type;
    private Integer amountPerson;
}
