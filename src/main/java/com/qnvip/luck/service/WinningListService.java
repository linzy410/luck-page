package com.qnvip.luck.service;

import java.util.List;

import com.qnvip.luck.entity.WinningList;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface WinningListService {

    List<WinningList> select(WinningList condition, String targetColumns, String otherCondition);

    WinningList selectOne(WinningList condition, String targetColumns, String otherCondition);

    WinningList selectById(Integer id);

}
