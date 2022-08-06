package com.app.housing_association.notice.service;

import com.app.housing_association.common.service.abstracts.AbstractCrudService;
import com.app.housing_association.notice.entity.Notice;
import com.app.housing_association.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoticeBasicService extends AbstractCrudService<Notice, Long> implements NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeBasicService(NoticeRepository noticeRepository) {
        super(noticeRepository);
        this.noticeRepository = noticeRepository;
    }

    @Override
    public List<Notice> findAllByArchived(Boolean archived) {
        return noticeRepository.findAllByArchived(archived);
    }

    @Override
    public long updateFixed() {
        var notices = updatedNotices();
        if (!notices.isEmpty()) {
            noticeRepository.saveAll(updatedNotices());
        }
        return notices.size();
    }

    private Notice updateArchived(Notice notice) {
        var currentDate = LocalDate.now();
        if (currentDate.isAfter(notice.getExecutionDate())) {
            notice.setArchived(true);
        }
        return notice;
    }

    private List<Notice> updatedNotices() {
        return noticeRepository
                .findAll()
                .stream()
                .filter(notice -> !notice.isArchived())
                .map(this::updateArchived)
                .filter(Notice::isArchived)
                .toList();
    }
}