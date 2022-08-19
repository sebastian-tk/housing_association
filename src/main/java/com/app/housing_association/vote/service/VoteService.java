package com.app.housing_association.vote.service;

import com.app.housing_association.common.service.abstracts.CrudService;
import com.app.housing_association.vote.entity.Vote;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VoteService extends CrudService<Vote, Long> {

    Vote saveWithFile(Vote inputVoteWithFile, MultipartFile file);

    Optional<Vote> updateWithFile(Vote input, MultipartFile file);

    byte[] convertFileById(Vote faultDto);

    List<Vote> findAllOrByFinished(Boolean finished);
}
