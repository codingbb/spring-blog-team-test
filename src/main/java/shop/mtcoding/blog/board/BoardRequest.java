package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private Integer id;
        private String title;
        private String content;

    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String content;
    }

}
