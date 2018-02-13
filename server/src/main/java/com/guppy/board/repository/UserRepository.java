package com.guppy.board.repository;

import com.guppy.board.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guppy.kang on 2018. 2. 7.
 * email : guppy.kang@kakaocorp.com
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUserKey(String userKey);

}
