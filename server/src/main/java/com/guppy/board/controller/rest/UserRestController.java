package com.guppy.board.controller.rest;

import com.guppy.board.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by guppy.kang on 2018. 2. 12.
 * email : guppy.kang@kakaocorp.com
 */

@RestController
@CrossOrigin(value = "*", origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping(value = "/api/user")
public class UserRestController {

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<User> getUserInfo (HttpSession session) {

        User user = (User) session.getAttribute("user");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
