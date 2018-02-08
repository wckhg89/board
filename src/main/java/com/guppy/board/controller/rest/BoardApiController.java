package com.guppy.board.controller.rest;

import com.guppy.board.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by guppy.kang on 2018. 2. 7.
 * email : guppy.kang@kakaocorp.com
 */


@RestController
@RequestMapping(value = "/api/board")
public class BoardApiController {

    private static final Logger logger = LoggerFactory.getLogger(BoardApiController.class);

    @GetMapping("/write")
    public ResponseEntity<User> writeBoard (HttpSession session) {

        User user = (User) session.getAttribute("user");

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }



}
