package com.qnvip.luck.controller;

import com.qnvip.commons.mybatis.service.BaseService;
import com.qnvip.commons.mybatis.util.UpdateMap;
import com.qnvip.luck.bo.ActivityBO;
import com.qnvip.luck.bo.PrizeBO;
import com.qnvip.luck.entity.Activity;
import com.qnvip.luck.entity.DefaultNumber;
import com.qnvip.luck.entity.LotteryNumber;
import com.qnvip.luck.service.ActivityService;
import com.qnvip.luck.service.DefaultNumberService;
import com.qnvip.luck.service.LotteryNumberService;
import com.qnvip.luck.service.PrizeService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author EricLin
 * @Date 2019/10/28 21:54
 */
@Controller
@RequestMapping("/manage/activity/")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private LotteryNumberService lotteryNumberService;
    @Autowired
    private DefaultNumberService defaultNumberService;
    @Autowired
    private BaseService baseService;



    @GetMapping("")
    public String list(Model model) {
        List<Activity> activities = activityService.select(new Activity(), null, null);
        model.addAttribute("activitis", activities);
        return "manage/activity/list";
    }


    @GetMapping("input")
    public String input(Integer id, Model model) {
        if (id != null && id > 0) {
            Activity activity = activityService.selectById(id);
            model.addAttribute(activity);
        }
        return "manage/activity/input";
    }

    @PostMapping("")
    public String save(ActivityBO activityBO) {
        activityService.save(activityBO);
        return "redirect:/manage/activity/";
    }

    @GetMapping("{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Activity activity = activityService.selectById(id);

        List<PrizeBO> prizes = prizeService.selectPrizeBOByActivityId(id);
        List<LotteryNumber> lotteryNumbers = lotteryNumberService.selectByActivityId(id);

        model.addAttribute("activity", activity);
        model.addAttribute("prizes", prizes);
        model.addAttribute("startLotteryNumber", lotteryNumbers.get(0).getNumber());
        model.addAttribute("endLotteryNumber", lotteryNumbers.get(lotteryNumbers.size() - 1).getNumber());
        return "manage/activity/detail";
    }

    @PostMapping("update/defaultNumber")
    public String updateDefautlNumber(ActivityBO activityBO) {
        int id = activityBO.getActivity().getId();
        UpdateMap deleteMap = new UpdateMap(DefaultNumber.class);
        deleteMap.addWhere("activityId", id);
        baseService.delete(deleteMap);

        activityBO.getDefaultNumbers().forEach(defaultNumber -> {
            if (StringUtils.isNotEmpty(defaultNumber.getNumber())) {
                for (String number : defaultNumber.getNumber().split(",")) {
                    DefaultNumber bean = new DefaultNumber();
                    bean.setNumber(number);
                    bean.setActivityId(id);
                    bean.setPrizeId(defaultNumber.getPrizeId());
                    baseService.insert(bean);
                }
            }
        });
        return "redirect:/manage/activity/";
    }

}
