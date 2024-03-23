package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    public BoardResponse.DetailDTO 글상세보기(Integer boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        return new BoardResponse.DetailDTO(board, sessionUser);
    }

    public Board 글수정조회(Integer boardId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글 존재하지 않음"));

        return board;
    }

    @Transactional
    public void 글수정(Integer boardId, Integer sessionUserId, BoardRequest.UpdateDTO requestDTO) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글 존재하지 않음"));

        if (board.getUser().getId() != sessionUserId) {
            throw new Exception403("게시글 수정 권한 없음");
        }

        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());

    }

    @Transactional
    public void 글쓰기(User sessionUserId, BoardRequest.SaveDTO requestDTO) {
        boardJPARepository.save(requestDTO.toEntity(sessionUserId));
    }

    @Transactional
    public void 글삭제(Integer boardId, Integer sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("삭제할 게시글이 없습니다"));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("삭제 권한 없음");
        }

        boardJPARepository.delete(board);
    }

    public List<Board> 글목록보기() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll(sort);

    }
}
