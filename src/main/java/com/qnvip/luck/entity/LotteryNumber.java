package com.qnvip.luck.entity;

import com.qnvip.commons.mybatis.annotation.Database;
import lombok.Data;

/**
 * @author Eric Lin
 * 2019-10-26
 */
@Database(tableName = "lc_lottery_number")
@Data
public class LotteryNumber {


    /**
     * 
     */
    private Integer id;

    /**
     * 号码
     */
    private String number;

    /**
     * 0=初始状态 10=中奖
     */
    private Integer status;

    /**
     * 
     */
    private String createTime;
}
