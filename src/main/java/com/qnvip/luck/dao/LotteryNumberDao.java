package com.qnvip.luck.dao;

import java.text.MessageFormat;
import java.util.List;

import java.util.Map;
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

    @SelectProvider(type = LotteryNumberSqlProvider.class, method = "batchAdd")
    int batchAdd(@Param("numbers") List<LotteryNumber> numbers);

    class LotteryNumberSqlProvider {
        public String batchAdd(Map<String, List<LotteryNumber>> map) {
            List<LotteryNumber> numbers = map.get("numbers");
            StringBuilder sql = new StringBuilder();
            sql.append("insert into lc_lottery_number (activityId,number,createTime) value ");
            MessageFormat mf = new MessageFormat("(#'{'numbers[{0}].activityId}, #'{'numbers[{0}].number}, #'{'numbers[{0}].createTime})");
            for (int i = 0; i < numbers.size(); i++) {
                sql.append(mf.format(new Object[] {i}));
                if (i < numbers.size() - 1) {
                    sql.append(",");
                }
            }
            return sql.toString();
        }

    }
}
