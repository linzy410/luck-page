package com.qnvip.luck.service.impl;

import com.qnvip.commons.enums.StatusEnum;
import com.qnvip.luck.dao.LotteryNumberDao;
import com.qnvip.luck.entity.LotteryNumber;
import com.qnvip.luck.service.LotteryNumberService;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
@Service
public class LotteryNumberServiceImpl implements LotteryNumberService {

    @Resource
    private LotteryNumberDao lotteryNumberDao;

    @Override
    public List<LotteryNumber> select(LotteryNumber condition, String targetColumns, String otherCondition) {
        return lotteryNumberDao.select(condition, targetColumns, otherCondition);
    }

    @Override
    public LotteryNumber selectOne(LotteryNumber condition, String targetColumns, String otherCondition) {
        if (StringUtils.isEmpty(otherCondition)) {
            otherCondition = "limit 1";
        } else {
            otherCondition += " limit 1";
        }
        List<LotteryNumber> list = lotteryNumberDao.select(condition, targetColumns, otherCondition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<LotteryNumber> selectByActivityId(Integer activityId) {
        if (activityId == null || activityId == 0) {
            return null;
        }
        LotteryNumber condition = new LotteryNumber();
        condition.setActivityId(activityId);
        return this.select(condition, null, null);
    }

    @Override
    public List<LotteryNumber> selectUnwingingByActivityId(Integer activityId) {
        if (activityId == null || activityId == 0) {
            return null;
        }
        LotteryNumber condition = new LotteryNumber();
        condition.setActivityId(activityId);
        condition.setStatus(StatusEnum.NOMAL.getValue());
        return this.select(condition, null, null);
    }
}
