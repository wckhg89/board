package com.guppy.board.service;

import com.guppy.board.domain.User;
import com.guppy.board.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guppy.kang on 2018. 2. 7.
 * email : guppy.kang@kakaocorp.com
 */

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginComplete (Authentication authentication) {
        User loginUser = User.initUser(authentication);
        User dbUser = userRepository.findByUserKey(loginUser.getUserKey());

        if (dbUser != null) {
            return loginUser;
        }

        //$2a$10$/L4lnbQy2Myy//jnnuHBNORmAkEcKuVWC6wE9H62GYfpOtAo7QyI2
        userRepository.save(loginUser);

        return loginUser;
    }




}
