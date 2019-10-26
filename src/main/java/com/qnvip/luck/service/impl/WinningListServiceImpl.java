package com.qnvip.luck.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.qnvip.luck.dao.WinningListDao;
import com.qnvip.luck.entity.WinningList;
import com.qnvip.luck.service.WinningListService;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
@Service
public class WinningListServiceImpl implements WinningListService {

    @Resource
    private WinningListDao winningListDao;

    @Override
    public List<WinningList> select(WinningList condition, String targetColumns, String otherCondition) {
        return winningListDao.select(condition, targetColumns, otherCondition);
    }

    @Override
    public WinningList selectOne(WinningList condition, String targetColumns, String otherCondition) {
        if (StringUtils.isEmpty(otherCondition)) {
            otherCondition = "limit 1";
        } else {
            otherCondition += " limit 1";
        }
        List<WinningList> list = winningListDao.select(condition, targetColumns, otherCondition);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public WinningList selectById(Integer id) {
        if (id == null || id == 0) {
            return null;
        }
        WinningList condition = new WinningList();
        condition.setId(id);
        return this.selectOne(condition, null, null);
    }
}
