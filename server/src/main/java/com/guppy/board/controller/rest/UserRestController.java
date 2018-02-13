package com.guppy.board.controller.rest;

import com.guppy.board.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by guppy.kang on 2018. 2. 12.
 * email : guppy.kang@kakaocorp.com
 */

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    @GetMapping("/info")
    public ResponseEntity<User> writeBoard (HttpSession session) {

        User user = (User) session.getAttribute("user");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
