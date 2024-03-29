package com.app.housing_association.fault.service;

import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.common.service.files.ImageServiceAzure;
import com.app.housing_association.fault.entity.Fault;
import com.app.housing_association.fault.repository.FaultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.app.housing_association.common.utils.IValidation.FAULT_IMAGE_VALIDATION;
import static com.app.housing_association.common.utils.IValidation.FAULT_VALIDATION;
import static java.util.Objects.isNull;

@Service
public class FaultBasicService extends AbstractCrudService<Fault, Long> implements FaultService {

    private final FaultRepository faultRepository;
    private final ImageServiceAzure imageService;

    public FaultBasicService(FaultRepository faultRepository, ImageServiceAzure imageService) {
        super(faultRepository);
        this.faultRepository = faultRepository;
        this.imageService = imageService;
    }


    @Transactional
    @Override
    public Fault saveWithImage(Fault inputFault, MultipartFile image) {
        if (isNull(inputFault)) {
            throw new IllegalArgumentException(FAULT_VALIDATION);
        }
        if (isNull(image)) {
            throw new IllegalArgumentException(FAULT_IMAGE_VALIDATION);
        }
        var id = faultRepository.getNextSeriesId();
        String imageName = imageService.checkAndSaveFile(image, id);
        inputFault.setImagePath(imageName);
        return faultRepository.save(inputFault);
    }

    @Override
    public Optional<Fault> updateWithImage(Fault input, MultipartFile image) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException(FAULT_VALIDATION);
        }
        return faultRepository
                .findById(input.getId())
                .map(fault -> {
                    fault.setTitle(Objects.nonNull(input.getTitle()) ? input.getTitle() : fault.getTitle());
                    fault.setDescription(Objects.nonNull(input.getDescription()) ? input.getDescription() : fault.getDescription());
                    fault.setImagePath(updateImage(fault, image));
                    fault.setBuilding(Objects.nonNull(input.getBuilding()) ? input.getBuilding() : fault.getBuilding());
                    return fault;
                })
                .map(faultRepository::save);
    }

    @Override
    public Optional<Fault> updateFixed(Fault input, Boolean fixed) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException(FAULT_VALIDATION);
        }
        return Optional.of(input)
                .map(fault -> {
                    fault.setFixed(fixed);
                    return fault;
                })
                .map(faultRepository::save);
    }

    @Override
    public byte[] convertImageById(Fault faultDto) {
        if (isNull(faultDto)) {
            throw new IllegalArgumentException(FAULT_VALIDATION);
        }
        return loadImageFromPath(faultDto.getImagePath());
    }

    @Override
    public List<Fault> findAllWithFixed(boolean fixed) {
        return faultRepository.findAllByFixed(fixed);
    }

    private byte[] loadImageFromPath(String path) {
        return imageService.downloadFile(path);
    }

    private String updateImage(Fault existFault, MultipartFile image) {
        if (Objects.isNull(image)) {
            return existFault.getImagePath();
        }
        return imageService.updateFile(image, existFault.getId());
    }
}
