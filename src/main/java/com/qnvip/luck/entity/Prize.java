package com.qnvip.luck.entity;

import com.qnvip.commons.mybatis.annotation.Database;
import lombok.Data;

/**
 * @author Eric Lin
 * 2019-10-26
 */
@Database(tableName = "lc_prize")
@Data
public class Prize {


    /**
     * 
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 
     */
    private String createTime;
}
