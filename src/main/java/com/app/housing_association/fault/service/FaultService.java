package com.app.housing_association.fault.service;

import com.app.housing_association.common.service.abstracts.CrudService;
import com.app.housing_association.fault.entity.Fault;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FaultService extends CrudService<Fault, Long> {

    Fault saveWithImage(Fault inputFaultWithImage, MultipartFile image);

    Optional<Fault> updateWithImage(Fault input, MultipartFile image);

    Optional<Fault> updateFixed(Fault input,Boolean fixed) ;

    byte[] convertImageById(Fault faultDto);

    List<Fault> findAllWithFixed(boolean fixed);

}
