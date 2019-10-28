package com.qnvip.luck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author EricLin
 * @Date 2019/10/28 21:20
 */
@Controller
public class HomeController {


    @GetMapping(value = {"/", "/index.html"})
    public String index() {

        return "manage/index";
    }


    @GetMapping("/dashborad")
    public String dashborad() {
        return "manage/dashborad";
    }
}
