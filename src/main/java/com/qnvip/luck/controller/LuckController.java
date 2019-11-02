package com.qnvip.luck.controller;

import com.qnvip.commons.bo.JsonResult;
import com.qnvip.commons.mybatis.service.BaseService;
import com.qnvip.commons.tool.StringUtil;
import com.qnvip.luck.entity.Activity;
import com.qnvip.luck.entity.LotteryNumber;
import com.qnvip.luck.entity.Prize;
import com.qnvip.luck.service.ActivityService;
import com.qnvip.luck.service.DefaultNumberService;
import com.qnvip.luck.service.LotteryNumberService;
import com.qnvip.luck.service.PrizeService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author EricLin
 * @Date 2019/11/2 10:50
 */
@RequestMapping("/luck/")
@Controller
public class LuckController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private DefaultNumberService defaultNumberService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private LotteryNumberService lotteryNumberService;

    @GetMapping("{id}")
    public String goLuck(@PathVariable(value = "id") Integer activityId, Model model) {
        Activity activity = activityService.selectById(activityId);
        List<Prize> prizes = prizeService.selectByActivityId(activityId);

        model.addAttribute("activity", activity);
        model.addAttribute("prizes", prizes);
        return "luck/index";
    }

    @GetMapping("lettery/number/{id}")
    @ResponseBody
    public JsonResult getLotteryNumbers(@PathVariable(value = "id") Integer activityId)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<LotteryNumber> lotteryNumbers = lotteryNumberService.selectUnwingingByActivityId(activityId);
        String numbers = StringUtil.getColumnValue(lotteryNumbers, "number");
        return JsonResult.success(numbers);
    }

}
