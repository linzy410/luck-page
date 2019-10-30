package com.qnvip.luck.service.impl;

import com.qnvip.commons.mybatis.mapper.Assembler;
import com.qnvip.commons.tool.DateUtil;
import com.qnvip.commons.tool.StringUtil;
import com.qnvip.luck.bo.ActivityBO;
import com.qnvip.luck.dao.LotteryNumberDao;
import com.qnvip.luck.entity.DefaultNumber;
import com.qnvip.luck.entity.LotteryNumber;
import com.qnvip.luck.entity.Prize;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.qnvip.luck.dao.ActivityDao;
import com.qnvip.luck.entity.Activity;
import com.qnvip.luck.service.ActivityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eric Lin
 *
 * 2019-10-28
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;
    @Resource
    private Assembler assembler;
    @Resource
    private LotteryNumberDao lotteryNumberDao;

    @Override
    public List<Activity> select(Activity condition, String targetColumns, String otherCondition) {
        return activityDao.select(condition, targetColumns, otherCondition);
    }

    @Override
    public Activity selectOne(Activity condition, String targetColumns, String otherCondition) {
        if (StringUtils.isEmpty(otherCondition)) {
            otherCondition = "limit 1";
        } else {
            otherCondition += " limit 1";
        }
        List<Activity> list = activityDao.select(condition, targetColumns, otherCondition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Activity selectById(Integer id) {
        if (id == null || id == 0) {
            return null;
        }
        Activity condition = new Activity();
        condition.setId(id);
        return this.selectOne(condition, null, null);
    }

    @Override
    @Transactional
    public void save(ActivityBO activityBO) {
        Activity activity = activityBO.getActivity();
        activity.setCreateTime(DateUtil.getCurrentDateTime());
        assembler.insert(activity);

        for (int i = 0; i < activityBO.getPrizes().size(); i++) {
            Prize prize = activityBO.getPrizes().get(i);
            if (StringUtils.isNotEmpty(prize.getName())) {
                prize.setActivityId(activity.getId());
                assembler.insert(prize);
                DefaultNumber number = activityBO.getDefaultNumbers().get(i);
                if (StringUtils.isNotEmpty(number.getNumber())) {
                    for (String numberStr : number.getNumber().split(",")) {
                        DefaultNumber bean = new DefaultNumber();
                        bean.setNumber(numberStr);
                        bean.setActivityId(activity.getId());
                        bean.setPrizeId(prize.getId());
                        assembler.insert(bean);
                    }
                }
            }
        }
        String numberPrefix = StringUtils.isEmpty(activityBO.getNumberPrefix()) ? StringUtils.EMPTY : activityBO.getNumberPrefix();
        List<LotteryNumber> lotteryNumbers = new ArrayList<>();
        int numberLength = String.valueOf(activityBO.getEndLotteryNumber()).length();
        for (int i = activityBO.getStartLotteryNumber(); i <= activityBO.getEndLotteryNumber(); i++) {
            LotteryNumber number = new LotteryNumber(numberPrefix + StringUtil.zeroFill(i, numberLength));
            number.setActivityId(activity.getId());
            lotteryNumbers.add(number);
        }
        lotteryNumberDao.batchAdd(lotteryNumbers);
    }
}
