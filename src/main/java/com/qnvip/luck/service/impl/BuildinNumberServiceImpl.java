package com.qnvip.luck.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.qnvip.luck.dao.BuildinNumberDao;
import com.qnvip.luck.entity.BuildinNumber;
import com.qnvip.luck.service.BuildinNumberService;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
@Service
public class BuildinNumberServiceImpl implements BuildinNumberService {

    @Resource
    private BuildinNumberDao buildinNumberDao;

    @Override
    public List<BuildinNumber> select(BuildinNumber condition, String targetColumns, String otherCondition) {
        return buildinNumberDao.select(condition, targetColumns, otherCondition);
    }

    @Override
    public BuildinNumber selectOne(BuildinNumber condition, String targetColumns, String otherCondition) {
        if (StringUtils.isEmpty(otherCondition)) {
            otherCondition = "limit 1";
        } else {
            otherCondition += " limit 1";
        }
        List<BuildinNumber> list = buildinNumberDao.select(condition, targetColumns, otherCondition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public BuildinNumber selectById(Integer id) {
        if (id == null || id == 0) {
            return null;
        }
        BuildinNumber condition = new BuildinNumber();
        condition.setId(id);
        return this.selectOne(condition, null, null);
    }
}
