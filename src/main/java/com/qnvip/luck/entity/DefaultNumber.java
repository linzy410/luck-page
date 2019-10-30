package com.qnvip.luck.entity;

import com.qnvip.commons.mybatis.annotation.Database;
import lombok.Data;

/**
 * @author Eric Lin
 * 2019-10-26
 */
@Database(tableName = "lc_default_number")
@Data
public class DefaultNumber {


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
     * 0=初始状态 10=已抽
     */
    private Integer status;

    /**
     * 
     */
    private Integer prizeId;
}
