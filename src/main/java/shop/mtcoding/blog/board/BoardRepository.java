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
    public Board save(Board board) {
        em.persist(board);
        return board;

    }

    public List<Board> findAll() {
        String q = """
                select b from Board b order by b.id desc
                """;
        Query query = em.createQuery(q, Board.class);
        List<Board> boardList =  query.getResultList();
        return boardList;

    }

    public Board findById(Integer boardId) {
        Board board = em.find(Board.class, boardId);
        return board;

    }

    @Transactional
    public void deleteById(Integer boardId) {
        String q = """
                delete from Board b where b.id = :id
                """;
        Query query = em.createQuery(q);
        query.setParameter("id", boardId);
        query.executeUpdate();

    }

    @Transactional
    public void updateById(Integer boardId, BoardRequest.UpdateDTO requestDTO) {
        Board board = findById(boardId);
        board.update(requestDTO);

    }

    public Board findByIdJoinUser(Integer id) {
        String q = """
                select b from Board b join fetch b.user u where b.id = :id
                """;
        Query query = em.createQuery(q, Board.class);
        query.setParameter("id", id);
        Board board = (Board) query.getSingleResult();
        return board;
    }
}
