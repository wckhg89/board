package com.guppy.board.repository;

import com.guppy.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guppy.kang on 2018. 2. 4.
 * email : guppy.kang@kakaocorp.com
 */
public interface BoardRepository extends MongoRepository<Board, Long>{

    Page<Board> findAll(Pageable pageable);
}
