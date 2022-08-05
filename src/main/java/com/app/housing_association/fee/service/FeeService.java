package com.app.housing_association.fee.service;

import com.app.housing_association.common.service.abstracts.CrudService;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.fee.entity.model.DataForCalculationFee;

public interface FeeService extends CrudService<Fee, Long> {

    Fee calculateAndSaveFeeByData(DataForCalculationFee data);
}
