package com.app.housing_association.notice.service;

import com.app.housing_association.common.service.abstracts.CrudService;
import com.app.housing_association.notice.entity.Notice;

import java.util.List;

public interface NoticeService extends CrudService<Notice, Long> {

    List<Notice> findAllByArchived(Boolean archived);

    long updateFixed();
}
