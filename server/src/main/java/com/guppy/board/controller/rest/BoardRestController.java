package com.guppy.board.controller.rest;

import com.guppy.board.domain.Board;
import com.guppy.board.domain.User;
import com.guppy.board.service.board.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by guppy.kang on 2018. 2. 7.
 * email : guppy.kang@kakaocorp.com
 */


@RestController
@RequestMapping(value = "/api/board")
public class BoardRestController {

    private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

    private BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeBoard (HttpSession session,
                                            @RequestBody Board board) {

        User user = (User) session.getAttribute("user");
        board.setUser(user);

        boardService.save(board);

        return new ResponseEntity<>("{\"ok\":\"Registration succeeded\"}",HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Board>> findBoards (Pageable pageable) {

        return boardService.findByMethod(pageable);
    }





}
