package com.app.housing_association.vote.service;

import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.common.service.files.PdfService;
import com.app.housing_association.vote.entity.Vote;
import com.app.housing_association.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.app.housing_association.common.service.files.FileService.TEMP_PATH;
import static com.app.housing_association.common.utils.IValidation.FAULT_VALIDATION;
import static com.app.housing_association.common.utils.IValidation.VOTE_VALIDATION;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class VoteBasicService extends AbstractCrudService<Vote, Long> implements VoteService {

    private final VoteRepository voteRepository;
    private final PdfService pdfService;

    public VoteBasicService(VoteRepository voteRepository, PdfService pdfService) {
        super(voteRepository);
        this.voteRepository = voteRepository;
        this.pdfService = pdfService;
    }


    @Transactional
    @Override
    public Vote saveWithFile(Vote input, MultipartFile file) {
        if (isNull(input)) {
            throw new IllegalArgumentException(VOTE_VALIDATION);
        }
        input.setFilePath(TEMP_PATH);
        Vote savedVote = voteRepository.save(input);
        if (nonNull(file)) {
            String imageName = pdfService.saveFile(file, savedVote.getId());
            savedVote.setFilePath(imageName);
            return voteRepository.save(savedVote);
        }
        return savedVote;
    }

    @Override
    public Optional<Vote> updateWithFile(Vote input, MultipartFile pdf) {
        if (isNull(input)) {
            throw new IllegalArgumentException(FAULT_VALIDATION);
        }
        return voteRepository
                .findById(input.getId())
                .map(vote -> {
                    vote.setTitle(nonNull(input.getTitle()) ? input.getTitle() : vote.getTitle());
                    vote.setDescription(nonNull(input.getDescription()) ? input.getDescription() : vote.getDescription());
                    vote.setFilePath(updatePdf(vote, pdf));
                    vote.setDateFinish(nonNull(input.getDateFinish()) ? input.getDateFinish() : vote.getDateFinish());
                    vote.setBuilding(nonNull(input.getBuilding()) ? input.getBuilding() : vote.getBuilding());
                    return vote;
                })
                .map(voteRepository::save);
    }


    @Override
    public byte[] convertFileById(Vote vote) {
        if (isNull(vote)) {
            throw new IllegalArgumentException(VOTE_VALIDATION);
        }
        return loadPdfFromPath(vote.getFilePath());
    }

    @Override
    public List<Vote> findAllOrByFinished(Boolean finished) {
        return isNull(finished) ? voteRepository.findAll() : voteRepository.findAllByFinished(finished);
    }

    @Override
    public long updateFinishedStatus() {
        var votes = updateVotesDates();
        if (!votes.isEmpty()) {
            voteRepository.saveAll(updateVotesDates());
        }
        return votes.size();
    }

    private Vote updateArchived(Vote vote) {
        var currentDate = LocalDate.now();
        if (currentDate.isAfter(vote.getDateFinish())) {
            vote.setFinished(true);
        }
        return vote;
    }

    private List<Vote> updateVotesDates() {
        return voteRepository
                .findAll()
                .stream()
                .filter(vote -> !vote.getFinished())
                .map(this::updateArchived)
                .filter(Vote::getFinished)
                .toList();
    }


    private byte[] loadPdfFromPath(String path) {
        return pdfService.convertToByte(path);
    }

    private String updatePdf(Vote existVote, MultipartFile file) {
        if (isNull(file)) {
            return existVote.getFilePath();
        }
        return pdfService.updateFile(file, existVote.getId());
    }
}
