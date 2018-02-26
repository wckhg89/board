package com.guppy.board.service.user;

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

    public User loginComplete (Authentication authentication, String type) {
        User loginUser = User.initUser(authentication, type);
        User dbUser = userRepository.findByUserKey(loginUser.getUserKey());

        if (dbUser != null) {
            return loginUser;
        }

        userRepository.save(loginUser);

        return loginUser;
    }




}
