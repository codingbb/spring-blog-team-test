package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO requestDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(requestDTO.getUsername());

        if (userOP.isPresent()) {
            throw new Exception400("회원가입 중복!");
        }

        userJPARepository.save(requestDTO.toEntity());
    }

    public User 로그인(UserRequest.LoginDTO requestDTO) {
        User sessionUser = userJPARepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다.!"));

        return sessionUser;

    }

    public User 회원조회(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보 찾을 수 없음"));

        return user;
    }

    @Transactional
    public User 회원수정(int id, UserRequest.UpdateDTO requestDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보 찾을 수 없음"));

        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());
        return user;
    }

}