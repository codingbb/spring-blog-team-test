package shop.mtcoding.blog.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void save_test() {
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        userJPARepository.save(user);

    }

    @Test
    public void findById_test() {
        int id = 1;

        Optional<User> userOp = userJPARepository.findById(id);

        if (userOp.isPresent()) {
            User user = userOp.get();
            System.out.println("findById_test : " + user.getUsername());
        }

    }

    @Test
    public void findAll_test() {
        userJPARepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

    }

    @Test
    public void findBUsernameAndPassword_test() {
        String username = "ssar";
        String password = "1234";

        userJPARepository.findByUsernameAndPassword(username, password);

    }

    @Test
    public void findAll_paging_test() throws JsonProcessingException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 2, sort);

        Page<User> userPG = userJPARepository.findAll(pageable);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userPG);
        System.out.println(json);

    }

}
