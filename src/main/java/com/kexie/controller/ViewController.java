package com.kexie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图控制层
 * @author 张俊龙
 */
@Controller
public class ViewController {

    @GetMapping("/view")
    public String view(String url){
        return url;
    }

}
