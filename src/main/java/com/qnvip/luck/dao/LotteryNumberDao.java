package com.qnvip.luck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.qnvip.luck.entity.LotteryNumber;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface LotteryNumberDao {

    @SelectProvider(type = com.qnvip.commons.mybatis.sqlprovider.SqlProvider.class, method = "select")
    List<LotteryNumber> select(@Param("condition") Object condition, @Param("targetColumns") String targetColumns,
            @Param("otherCondition") String otherCondition);
}
