package com.qnvip.luck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.qnvip.luck.entity.WinningList;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface WinningListDao {

    @SelectProvider(type = com.qnvip.commons.mybatis.sqlprovider.SqlProvider.class, method = "select")
    List<WinningList> select(@Param("condition") Object condition, @Param("targetColumns") String targetColumns,
            @Param("otherCondition") String otherCondition);
}
