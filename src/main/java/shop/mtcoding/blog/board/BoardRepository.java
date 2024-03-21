package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO) {
        String q = """
                insert into board_tb (title, content, created_at) values (?, ?, now())
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.executeUpdate();

    }

    public List<Board> findAll() {
        String q = """
                select * from board_tb order by id desc 
                """;
        Query query = em.createNativeQuery(q, Board.class);
        List<Board> boardList =  query.getResultList();
        return boardList;

    }

    public Board findById(Integer boardId) {
        String q = """
                select * from board_tb where id = ?
                """;
        Query query = em.createNativeQuery(q, Board.class);
        query.setParameter(1, boardId);
        Board board = (Board) query.getSingleResult();
        return board;

    }
}
