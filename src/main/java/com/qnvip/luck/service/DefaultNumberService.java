package com.qnvip.luck.service;

import java.util.List;

import com.qnvip.luck.entity.DefaultNumber;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface DefaultNumberService {

    List<DefaultNumber> select(DefaultNumber condition, String targetColumns, String otherCondition);

    DefaultNumber selectOne(DefaultNumber condition, String targetColumns, String otherCondition);

    List<DefaultNumber> selectByActivityId(Integer activityId);

}
