package com.qnvip.luck.entity;

import com.qnvip.commons.enums.StatusEnum;
import com.qnvip.commons.mybatis.annotation.Database;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eric Lin
 * 2019-10-26
 */
@Database(tableName = "lc_lottery_number")
@Data
@NoArgsConstructor
public class LotteryNumber {


    /**
     * 
     */
    private Integer id;

    private Integer activityId;

    /**
     * 号码
     */
    private String number;

    /**
     * 0=初始状态 10=中奖
     */
    private Integer status;


    public LotteryNumber(String number) {
        this.number = number;
        this.status = StatusEnum.NOMAL.getValue();
    }
}
