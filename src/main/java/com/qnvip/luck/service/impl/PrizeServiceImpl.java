package com.qnvip.luck.service.impl;

import com.qnvip.luck.bo.PrizeBO;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.qnvip.luck.dao.PrizeDao;
import com.qnvip.luck.entity.Prize;
import com.qnvip.luck.service.PrizeService;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
@Service
public class PrizeServiceImpl implements PrizeService {

    @Resource
    private PrizeDao prizeDao;

    @Override
    public List<Prize> select(Prize condition, String targetColumns, String otherCondition) {
        return prizeDao.select(condition, targetColumns, otherCondition);
    }

    @Override
    public Prize selectOne(Prize condition, String targetColumns, String otherCondition) {
        if (StringUtils.isEmpty(otherCondition)) {
            otherCondition = "limit 1";
        } else {
            otherCondition += " limit 1";
        }
        List<Prize> list = prizeDao.select(condition, targetColumns, otherCondition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Prize> selectByActivityId(Integer activityid) {
        if (activityid == null || activityid == 0) {
            return null;
        }
        Prize condition = new Prize();
        condition.setActivityId(activityid);
        return this.select(condition, null, null);
    }

    @Override
    public List<PrizeBO> selectPrizeBOByActivityId(Integer activityid) {
        return prizeDao.selectPrizeBO(activityid);
    }
}
