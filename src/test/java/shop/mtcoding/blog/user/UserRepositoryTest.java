package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.BoardRepository;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameAndPassword() {
        UserRequest.LoginDTO requestDTO = new UserRequest.LoginDTO();
        requestDTO.setUsername("ssar");
        requestDTO.setPassword("1234");

        User user = userRepository.findByUsernameAndPassword(requestDTO);
        System.out.println(user);

        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");

    }


}
