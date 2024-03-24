package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final HttpSession session;

    //TODO : 글 목록조회 api 필요
    //TODO : 글 상세보기 api 필요
    //TODO : 글 조회(업데이트폼) api 필요

    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        Board board = boardService.글수정조회(id);
        return ResponseEntity.ok(new ApiUtil(board));
    }


    @GetMapping("/")
    public ResponseEntity<?> main() {
        List<BoardResponse.MainDTO> respDTO = boardService.글목록보기();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }


    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글수정(id, sessionUser.getId(), requestDTO);
        return ResponseEntity.ok(new ApiUtil(board));

    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil(null));
    }

    //완
    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글쓰기(sessionUser, requestDTO);

        return ResponseEntity.ok(new ApiUtil(board));
    }

}
