package com.qnvip.luck.service;

import com.qnvip.luck.bo.PrizeBO;
import java.util.List;

import com.qnvip.luck.entity.Prize;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface PrizeService {

    List<Prize> select(Prize condition, String targetColumns, String otherCondition);

    Prize selectOne(Prize condition, String targetColumns, String otherCondition);

    List<Prize> selectByActivityId(Integer activityid);

    Prize selectById(Integer id);

    List<PrizeBO> selectPrizeBOByActivityId(Integer activityid);

}
