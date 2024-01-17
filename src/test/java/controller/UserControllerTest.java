package controller;

import com.example.learningplatformsql.controller.UserController;
import com.example.learningplatformsql.entity.User;
import com.example.learningplatformsql.security.JWTTokenProvider;
import com.example.learningplatformsql.security.UserPrincipal;
import com.example.learningplatformsql.service.UserService;
import dto.UserDto.LoginUserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private JWTTokenProvider jwtTokenProvider;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("testUser");

        ResponseEntity<User> responseEntity = userController.create(user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testLogin() {
        LoginUserDto dto = new LoginUserDto();

        dto.setUsername("testUser");
        dto.setPassword("testPassword");

        when(userService.loadUserByUsername(anyString())).thenReturn(new UserPrincipal());
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        ResponseEntity<String> responseEntity = userController.login(dto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testGetUserById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userService.findById(id)).thenReturn(Optional.of(user));

        ResponseEntity<User> responseEntity = userController.getUserById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void testGetUserByIdNotFound() {
        Long id = 1L;

        when(userService.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<User> response = userController.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();

        when(userService.findAll()).thenReturn(userList);
        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteUserById() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = userController.deleteUserById(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userService.findById(id)).thenReturn(Optional.of(new User()));
        ResponseEntity<User> responseEntity = userController.updateUser(id, user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateUserNotFound() {
        Long id = 1L;
        User user = new User();
        when(userService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.updateUser(id, user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
