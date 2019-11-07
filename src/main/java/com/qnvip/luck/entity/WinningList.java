package com.qnvip.luck.entity;

import com.qnvip.commons.mybatis.annotation.Database;
import com.qnvip.commons.tool.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eric Lin
 * 2019-10-26
 */
@Database(tableName = "lc_winning_list")
@Data
@NoArgsConstructor
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

    public WinningList(Integer activityId, Integer prizeId, String prizeName, String number) {
        this.activityId = activityId;
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.number = number;
        this.createTime = DateUtil.getCurrentDateTime();
    }
}
