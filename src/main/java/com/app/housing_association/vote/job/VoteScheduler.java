package com.app.housing_association.vote.job;

import com.app.housing_association.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VoteScheduler {

    private final VoteService voteService;

    @Scheduled(cron = "${vote.scheduler.execution.duration}")
    public void checkAndChangeFinishedStatus() {
        log.info("Start check votes dates");
        var quantityChanges = voteService.updateFinishedStatus();
        log.info("Checking the dates of votes - finished, changes: {} votes", quantityChanges);
    }
}
