package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void updateById_test() {
        BoardRequest.UpdateDTO requestDTO = new BoardRequest.UpdateDTO();
        requestDTO.setTitle("허허");
        requestDTO.setContent("후후");

        Integer id = 1;

        boardRepository.updateById(id, requestDTO);

        List<Board> boardList = boardRepository.findAll();
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(3).getTitle()).isEqualTo("허허");

    }

    @Test
    public void delete_test() {
        Integer id = 1;

        boardRepository.delete(id);

        List<Board> boardList = boardRepository.findAll();
        Assertions.assertThat(boardList.size()).isEqualTo(3);

    }


    @Test
    public void findById_test() {
        //given
        Integer id = 1;

        //when
        Board board = boardRepository.findById(id);

        //then
        System.out.println("board_test : " + board.getTitle());
        System.out.println("board_test : " + board.getContent());

        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");

    }

    @Test
    public void findAll_test() {
        //given

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        System.out.println("boardList_test : " + boardList);
        System.out.println("boardList_test : " + boardList.size());

        Assertions.assertThat(boardList.size()).isEqualTo(4);

    }

    @Test
    public void save_test() {
        BoardRequest.SaveDTO requestDTO = new BoardRequest.SaveDTO();
        requestDTO.setTitle("훗");
        requestDTO.setContent("안녕");

        boardRepository.save(requestDTO);

    }


}
