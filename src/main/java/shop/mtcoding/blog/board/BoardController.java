package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Board board = boardRepository.findById(id);
        boardRepository.delete(board.getId());

        return "redirect:/";

    }


    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO) {
        boardRepository.save(requestDTO);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("board", board);

        return "board/detail";
    }
}
