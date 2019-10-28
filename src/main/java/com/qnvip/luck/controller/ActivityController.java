package com.qnvip.luck.controller;

import com.qnvip.luck.bo.ActivityBO;
import com.qnvip.luck.entity.Activity;
import com.qnvip.luck.service.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "redirect:/manage/activty/";
    }
}
