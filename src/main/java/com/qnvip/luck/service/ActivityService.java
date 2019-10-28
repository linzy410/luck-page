package com.qnvip.luck.service;

import com.qnvip.luck.bo.ActivityBO;
import java.util.List;

import com.qnvip.luck.entity.Activity;

/**
 * @author Eric Lin
 *
 * 2019-10-28
 */
public interface ActivityService {

    List<Activity> select(Activity condition, String targetColumns, String otherCondition);

    Activity selectOne(Activity condition, String targetColumns, String otherCondition);

    Activity selectById(Integer id);

    void save(ActivityBO activityBO);

}
