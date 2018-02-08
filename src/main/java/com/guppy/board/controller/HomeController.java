package com.guppy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by guppy.kang on 2018. 2. 8.
 * email : guppy.kang@kakaocorp.com
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index () {

        return "index";
    }
}
