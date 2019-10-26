package com.qnvip.luck.service;

import java.util.List;

import com.qnvip.luck.entity.BuildinNumber;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface BuildinNumberService {

    List<BuildinNumber> select(BuildinNumber condition, String targetColumns, String otherCondition);

    BuildinNumber selectOne(BuildinNumber condition, String targetColumns, String otherCondition);

    BuildinNumber selectById(Integer id);

}
