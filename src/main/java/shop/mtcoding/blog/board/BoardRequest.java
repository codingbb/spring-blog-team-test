package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private Integer id;
        private String title;
        private String content;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user) //세션에서 가져와야하기 때문에
                    .build();
        }

    }

    @Data
    public static class UpdateDTO {
//        private Integer id;
        private String title;
        private String content;
    }

}
