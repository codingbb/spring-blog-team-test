package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private Integer id;
        private String title;
        private String content;

        public Board toEntity() {
            return Board.builder().build();
        }

    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String content;
    }

}
