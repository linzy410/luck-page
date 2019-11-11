package com.qnvip.luck.entity;

import com.qnvip.commons.mybatis.annotation.Database;
import lombok.Data;

/**
 * @author Eric Lin
 * 2019-10-28
 */
@Database(tableName = "lc_activity")
@Data
public class Activity {


    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String title;

    private String numberPrefix;

    /**
     * 
     */
    private String createTime;
}
