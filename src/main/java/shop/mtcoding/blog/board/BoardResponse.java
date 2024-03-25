package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @AllArgsConstructor
    @Data
    public static class CountDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private Long replyCount;

    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private List<ReplyDTO> replies = new ArrayList<>();
        private boolean isOwner;

        public DetailDTO(Board board, List<Reply> replyList, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.isOwner = false;

            if (sessionUser != null) {
                if (board.getUser().getId() == userId) {
                    isOwner = true;
                }
            }

            this.replies = replyList.stream().map(reply ->
                    new ReplyDTO(reply, sessionUser)).toList();

        }

        @Data
        public class ReplyDTO {
            private Integer id;
            private String comment;
            private Integer userId;
            private String username;
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
                this.isOwner = false;

                if (sessionUser != null) {
                    if (reply.getUser().getId() == userId) {
                        isOwner = true;
                    }
                }
            }
        }

    }



    @Data
    public static class MainDTO {
        private Integer id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

//    @Data
//    public static class DetailDTO {
//        private Integer id;
//        private String title;
//        private String content;
//        private UserDTO user;
//        private Boolean isOwner;
//
//        public DetailDTO(Board board, User sessionUser) {
//            this.id = board.getId();
//            this.title = board.getTitle();
//            this.content = board.getContent();
//            this.user = new UserDTO(board.getUser());
//
//            this.isOwner = false;
//
//            if (sessionUser != null) {
//                if (sessionUser.getId() == board.getUser().getId()) {
//                    this.isOwner = true;
//                }
//            }
//        }
//
//        private class UserDTO {
//            private int id;
//            private String username;
//
//            public UserDTO(User user) {
//                this.id = user.getId();
//                this.username = user.getUsername();
//            }
//        }
//
//    }

}
