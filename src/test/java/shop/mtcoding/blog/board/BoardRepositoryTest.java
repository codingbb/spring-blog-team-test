package shop.mtcoding.blog.board;

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
