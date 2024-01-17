package service;

import com.example.learningplatformsql.entity.User;
import com.example.learningplatformsql.repository.UserRepository;
import com.example.learningplatformsql.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        // work with encoder
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        String username = "testuser";

        // work with encoder
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(id);

        assertTrue(foundUser.isPresent());
        assertEquals(id, foundUser.get().getId());
    }

    @Test
    public void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUsers = userService.findAll();

        assertEquals(2, foundUsers.size());
    }

    @Test
    public void testRemove() {
        User user = new User();

        userService.remove(user);

        userRepository.delete(user);
    }

    @Test
    public void testRemoveById() {
        Long id = 1L;

        userService.removeById(id);

        userRepository.deleteById(id);
    }

    @Test
    public void testUpdate() {
        User user = new User();

        userService.update(user);

        userRepository.save(user);
    }
}
