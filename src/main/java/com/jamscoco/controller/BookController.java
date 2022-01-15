package com.jamscoco.controller;


import com.jamscoco.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/jamscoco/book")
public class BookController {

    @GetMapping("test")
    public R test(){
        return R.ok();
    }

}

