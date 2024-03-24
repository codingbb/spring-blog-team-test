package shop.mtcoding.blog.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyJPARepository replyJPARepository;
    @Autowired
    private BoardJPARepository boardJPARepository;

    @Test
    public void save_test() {
        Reply reply = Reply.builder()
                .board(Board.builder().id(1).build())
                .user(User.builder().id(1).build())
                .comment("댓글5")
                .build();

        replyJPARepository.save(reply);
    }

    @Test
    public void findByBoardId_test() {
        int boardId = 4;

        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글 찾을 수 없음"));

        List<Reply> replyList = replyJPARepository.findByBoardId(boardId);

        board.setReplies(replyList);

        System.out.println(replyList.size());

    }

}
