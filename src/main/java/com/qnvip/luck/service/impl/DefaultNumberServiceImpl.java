package com.qnvip.luck.service.impl;

import com.qnvip.commons.enums.StatusEnum;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.qnvip.luck.dao.DefaultNumberDao;
import com.qnvip.luck.entity.DefaultNumber;
import com.qnvip.luck.service.DefaultNumberService;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
@Service
public class DefaultNumberServiceImpl implements DefaultNumberService {

    @Resource
    private DefaultNumberDao defaultNumberDao;

    @Override
    public List<DefaultNumber> select(DefaultNumber condition, String targetColumns, String otherCondition) {
        return defaultNumberDao.select(condition, targetColumns, otherCondition);
    }

    @Override
    public DefaultNumber selectOne(DefaultNumber condition, String targetColumns, String otherCondition) {
        if (StringUtils.isEmpty(otherCondition)) {
            otherCondition = "limit 1";
        } else {
            otherCondition += " limit 1";
        }
        List<DefaultNumber> list = defaultNumberDao.select(condition, targetColumns, otherCondition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DefaultNumber> selectUnwingByActivityId(Integer activityId, Integer prizeId) {
        if (activityId == null || activityId == 0) {
            return null;
        }
        DefaultNumber condition = new DefaultNumber();
        condition.setActivityId(activityId);
        condition.setPrizeId(prizeId);
        condition.setStatus(StatusEnum.NOMAL.getValue());
        return this.select(condition, null, null);
    }
}
