package com.guppy.board.controller;

import com.guppy.board.domain.User;
import com.guppy.board.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by guppy.kang on 2018. 2. 7.
 * email : guppy.kang@kakaocorp.com
 */

@Controller
public class OauthController {


    private static final Logger logger = LoggerFactory.getLogger(OauthController.class);

    private UserService userService;

    public OauthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/facebook/complete")
    public String facebookComplete (Principal principal, HttpSession httpSession) {
        if (principal == null) {
            // TODO : 익셉션 던지자
            return "error";
        }

        Authentication authentication
                = ((OAuth2Authentication) principal).getUserAuthentication();

        User user = userService.loginComplete(authentication);

        // todo : 만일 서버가 여러대이면 Redis와 같은 글로벌 캐시를 써서 세션 관리를 해야함
        httpSession.setAttribute("user", user);

        return "redirect:/";
    }

}
