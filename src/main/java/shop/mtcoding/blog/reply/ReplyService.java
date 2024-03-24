package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO requestDTO, User sessionUser) {
        Board board = boardJPARepository.findById(requestDTO.getBoardId())
                .orElseThrow(() -> new Exception404("없는 게시글에는 댓글을 쓸 수 없음!"));

        Reply reply = requestDTO.toEntity(sessionUser, board);
        replyJPARepository.save(reply);

    }

    @Transactional
    public void 댓글삭제(Integer replyId, Integer sessionUserId) {
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(() -> new Exception404("없는 댓글을 삭제할 수 없음"));

        if (reply.getUser().getId() != sessionUserId) {
            throw new Exception403("삭제 권한 없음!");
        }

        replyJPARepository.deleteById(replyId);

    }
}
