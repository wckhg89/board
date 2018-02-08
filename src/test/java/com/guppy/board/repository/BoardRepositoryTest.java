package com.guppy.board.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guppy.board.domain.Board;
import com.guppy.board.domain.Comment;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by guppy.kang on 2018. 2. 4.
 * email : guppy.kang@kakaocorp.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryTest.class);

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    @Rollback
    public void setUpData () {
        logger.info("Insert Test Board Data");
        Comment comment1 =
                Comment.builder()
                    .userName("김현우")
                    .message("첫번째 댓글")
                    .createdAt(new DateTime())
                    .build();

        Comment comment2 =
                Comment.builder()
                        .userName("조정훈")
                        .message("두번째 댓글")
                        .createdAt(new DateTime())
                        .build();

        List<Comment> commentList
                = Lists.newArrayList(comment1, comment2);

        Board board = Board.builder()
                .title("게시글1")
                .content("게시글 첫번째 내용")
                .createdAt(new DateTime())
                .comments(commentList).build();

        boardRepository.insert(board);
    }


    @Test
    public void findAllBoardTest () throws JsonProcessingException {
        logger.info("Find All Test Start");
        List<Board> boardList = boardRepository.findAll();

        assertNotNull(boardList);
        logger.info(objectMapper.writeValueAsString(boardList));
    }
}
