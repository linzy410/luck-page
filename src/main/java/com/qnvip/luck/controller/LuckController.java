package com.qnvip.luck.controller;

import com.qnvip.commons.bo.JsonResult;
import com.qnvip.commons.enums.ResultCode;
import com.qnvip.commons.enums.StatusEnum;
import com.qnvip.commons.mybatis.service.BaseService;
import com.qnvip.commons.mybatis.util.UpdateMap;
import com.qnvip.commons.tool.StringUtil;
import com.qnvip.luck.entity.Activity;
import com.qnvip.luck.entity.DefaultNumber;
import com.qnvip.luck.entity.LotteryNumber;
import com.qnvip.luck.entity.Prize;
import com.qnvip.luck.entity.WinningList;
import com.qnvip.luck.service.ActivityService;
import com.qnvip.luck.service.DefaultNumberService;
import com.qnvip.luck.service.LotteryNumberService;
import com.qnvip.luck.service.PrizeService;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
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
        Prize condition = new Prize();
        condition.setActivityId(activityId);
        Prize defautlPrize = prizeService.selectOne(condition, null, "order by listOrder desc");
        if (defautlPrize != null) {
            model.addAttribute("defaultPrizeId", defautlPrize.getId());
        }
        LotteryNumber numberConditNumber = new LotteryNumber();
        numberConditNumber.setActivityId(activityId);
        LotteryNumber lotteryNumber = lotteryNumberService.selectOne(numberConditNumber, null, null);
        String defaultNumber = StringUtil.stringFill(activity.getNumberPrefix(), lotteryNumber.getNumber().length(), '8', false);
        model.addAttribute("activity", activity);
        model.addAttribute("prizes", prizes);
        model.addAttribute("defaultNumber", defaultNumber);

        return "luck/index";
    }

    @GetMapping("lettery/number/{id}")
    @ResponseBody
    public JsonResult getLotteryNumbers(@PathVariable(value = "id") Integer activityId)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<LotteryNumber> lotteryNumbers = lotteryNumberService.selectUnwingingByActivityId(activityId);
        List<LotteryNumber> numberList = new ArrayList<>(100);
        for (int i = 0; i < 60; i++) {
            numberList.add(lotteryNumbers.get(new Random().nextInt(lotteryNumbers.size())));
        }
        String numbers = StringUtil.getColumnValue(numberList, "number");
        return JsonResult.success(numbers);
    }


    @GetMapping("go/{id}")
    @ResponseBody
    public JsonResult luck(@PathVariable(value = "id") Integer activityId, Integer prizeId) {
        List<DefaultNumber> list = defaultNumberService.selectUnwingByActivityId(activityId, prizeId);
        Prize prize = prizeService.selectById(prizeId);
//        if (prize.getBalance() <= 0) {
//            return JsonResult.error(ResultCode.BUSINESS_ERROR);
//        }
        String number = null;
        if (list != null && list.size() > 0) {
            DefaultNumber defaultNumber = list.get(0);
            UpdateMap updateMap = new UpdateMap(DefaultNumber.class);
            updateMap.addField("status", StatusEnum.SUCC.getValue());
            updateMap.addWhere("id", defaultNumber.getId());
            baseService.update(updateMap);
            number = defaultNumber.getNumber();
        }

        if (StringUtils.isEmpty(number)) {
            List<LotteryNumber> lotteryNumbers = lotteryNumberService.selectUnwingingByActivityId(activityId);
            Random random = new Random();
            LotteryNumber lotteryNumber = lotteryNumbers.get(random.nextInt(lotteryNumbers.size()));
            number = lotteryNumber.getNumber();
        }
        WinningList winningList = new WinningList(activityId, prizeId, prize.getName(), number);
        baseService.insert(winningList);

        UpdateMap updateMap = new UpdateMap(Prize.class);
        updateMap.addField("balance", "balance-1", true);
        updateMap.addWhere("id", prizeId);
        baseService.update(updateMap);

        UpdateMap lotteryNumberMap = new UpdateMap(LotteryNumber.class);
        lotteryNumberMap.addField("status", StatusEnum.SUCC.getValue());
        lotteryNumberMap.addWhere("number", number);
        baseService.update(lotteryNumberMap);
        return JsonResult.success(number);
    }

}
