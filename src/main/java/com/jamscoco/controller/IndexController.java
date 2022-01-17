package com.jamscoco.controller;

import com.jamscoco.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("test")
    public R test(){
        return R.ok();
    }
}
