package com.qnvip.luck.dao;

import com.qnvip.luck.bo.PrizeBO;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.qnvip.luck.entity.Prize;

/**
 * @author Eric Lin
 *
 * 2019-10-26
 */
public interface PrizeDao {

    @SelectProvider(type = com.qnvip.commons.mybatis.sqlprovider.SqlProvider.class, method = "select")
    List<Prize> select(@Param("condition") Object condition, @Param("targetColumns") String targetColumns,
            @Param("otherCondition") String otherCondition);


    @Select("select p.id, p.name, p.listOrder, p.amount, group_concat(dn.number) as defaultNumbers from lc_prize p"
            + " left join lc_default_number dn on p.id=dn.prizeId where p.activityId=#{activityId} group by p.id")
    List<PrizeBO> selectPrizeBO(@Param("activityId") Integer activityId);
}
