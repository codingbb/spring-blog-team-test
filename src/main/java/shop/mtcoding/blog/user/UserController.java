package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    //TODO : 회원정보 조회 API 필요

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO requestDTO) {
        User user = userService.회원가입(requestDTO);
        return ResponseEntity.ok(new ApiUtil(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        User sessionUser = userService.로그인(requestDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil(null));

    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@RequestBody UserRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.회원수정(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil(newSessionUser));
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> userinfo(@PathVariable Integer id) {
        UserResponse.DTO respDTO = userService.회원조회(id);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

}
