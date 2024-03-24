package shop.mtcoding.blog.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "board_tb")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient
    private boolean isBoardOwner;

    @JsonIgnore
    @OrderBy("id desc")
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    public void update(BoardRequest.UpdateDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
    }

}
