package com.app.housing_association.notice.job;

import com.app.housing_association.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoticeScheduler {

    private final NoticeService noticeService;

    @Scheduled(cron = "${notice.scheduler.execution.duration}" )
    public void checkAndChangeArchived(){
        log.info("start moving to the archive");
        var quantityTransferred = noticeService.updateFixed();
        log.info("Transfer to archive is completed, moved: {} notices",quantityTransferred);
    }
}
