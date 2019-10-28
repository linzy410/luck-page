package com.qnvip.luck.entity;

import com.qnvip.commons.mybatis.annotation.Database;
import lombok.Data;

/**
 * @author Eric Lin
 * 2019-10-26
 */
@Database(tableName = "lc_winning_list")
@Data
public class WinningList {


    /**
     * 
     */
    private Integer id;

    private Integer activityId;

    /**
     * 
     */
    private Integer prizeId;

    /**
     * 
     */
    private String prizeName;

    /**
     * 
     */
    private String number;

    /**
     * 
     */
    private String createTime;
}
