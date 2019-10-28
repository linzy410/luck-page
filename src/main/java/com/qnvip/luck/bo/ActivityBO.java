package com.qnvip.luck.bo;

import com.qnvip.luck.entity.Activity;
import com.qnvip.luck.entity.DefaultNumber;
import com.qnvip.luck.entity.Prize;
import java.util.List;
import lombok.Data;

/**
 * @Author EricLin
 * @Date 2019/10/28 22:45
 */
@Data
public class ActivityBO {

    private Activity activity;
    private List<Prize> prizes;
    private List<DefaultNumber> defaultNumbers;

    private String numberPrefix;
    private Integer startLotteryNumber;
    private Integer endLotteryNumber;
}
