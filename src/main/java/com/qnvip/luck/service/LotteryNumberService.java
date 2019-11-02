package com.qnvip.luck.service;

import java.util.List;

import com.qnvip.luck.entity.LotteryNumber;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface LotteryNumberService {

    List<LotteryNumber> select(LotteryNumber condition, String targetColumns, String otherCondition);

    LotteryNumber selectOne(LotteryNumber condition, String targetColumns, String otherCondition);

    List<LotteryNumber> selectByActivityId(Integer activityId);

    List<LotteryNumber> selectUnwingingByActivityId(Integer activityId);

}
