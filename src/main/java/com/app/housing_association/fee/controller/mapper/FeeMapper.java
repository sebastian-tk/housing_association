package com.app.housing_association.fee.controller.mapper;

import com.app.housing_association.common.controller.mapper.GenericMapper;
import com.app.housing_association.contract.controller.dto.ContractDto;
import com.app.housing_association.contract.entity.Contract;
import com.app.housing_association.fee.controller.dto.FeeDto;
import com.app.housing_association.fee.entity.Fee;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;

@Mapper(componentModel = "spring")
public interface FeeMapper extends GenericMapper<Fee, FeeDto, Long> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "heating", target = "heating")
    @Mapping(source = "renovationFund", target = "renovationFund")
    @Mapping(source = "rent", target = "rent")
    @Mapping(source = "exploitation", target = "exploitation")
    @Mapping(source = "administration", target = "administration")
    @Mapping(source = "warmWater", target = "warmWater")
    @Mapping(source = "coldWater", target = "coldWater")
    @Mapping(source = "sewage", target = "sewage")
    @Mapping(source = "rubbish", target = "rubbish")
    @Mapping(source = "contract", target = "contract")
    @BeanMapping(ignoreByDefault = true)
    @Override
    Fee toEntity(FeeDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    FeeDto toDto(Fee entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @BeanMapping(ignoreByDefault = true)
    Contract createContract(ContractDto dto);

    @InheritInverseConfiguration(name = "createContract")
    ContractDto createContractDto(Contract entity);
}
