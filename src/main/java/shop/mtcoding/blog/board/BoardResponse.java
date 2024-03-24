package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class MainDTO {
        private Integer id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private UserDTO user;
        private Boolean isOwner;

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDTO(board.getUser());

            this.isOwner = false;

            if (sessionUser != null) {
                if (sessionUser.getId() == board.getUser().getId()) {
                    this.isOwner = true;
                }
            }
        }

        private class UserDTO {
            private int id;
            private String username;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

    }

}
