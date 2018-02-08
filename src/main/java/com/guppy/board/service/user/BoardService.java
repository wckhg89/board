package com.guppy.board.service.user;

import com.guppy.board.domain.Board;
import com.guppy.board.domain.User;
import com.guppy.board.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by guppy.kang on 2018. 2. 8.
 * email : guppy.kang@kakaocorp.com
 */

@Service
public class BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    public ResponseEntity<HttpStatus> save (Board board){
        boardRepository.save(board);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
