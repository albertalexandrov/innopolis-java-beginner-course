package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.innopolis.Application;
import ru.innopolis.dto.UserCreateUpdateDTO;
import ru.innopolis.entity.User;
import ru.innopolis.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserCreateUpdateDTO userCreateDTO;

    private UserCreateUpdateDTO userUpdateDTO;

    private User user;

    @BeforeEach
    public void setUp() {
        var firstName = "Иван";
        var lastName = "Иванов";
        var phoneNumber = "+79991234567";
        userCreateDTO = UserCreateUpdateDTO
                .builder()
                .lastName(lastName)
                .firstName(firstName)
                .phoneNumber(phoneNumber)
                .build();
        userUpdateDTO = UserCreateUpdateDTO
                .builder()
                .lastName("new " + lastName)
                .firstName("new " + firstName)
                .phoneNumber("new " + phoneNumber)
                .build();
        user = User
                .builder()
                .id(1L)
                .lastName(lastName)
                .firstName(firstName)
                .phoneNumber(phoneNumber)
                .isDeleted(false)
                .build();
    }

    @Test
    void createUser_Created() throws Exception {
        when(userRepository.save(any(User.class))).thenReturn(user);
        ResultActions response = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", CoreMatchers.is(user.getPhoneNumber())));
    }

    @Test
    void getUser() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.of(user));
        ResultActions response = mockMvc.perform(get("/user/{id}", user.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", CoreMatchers.is(user.getPhoneNumber())));
    }

    @Test
    void getUser_UserNotFound() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(get("/user/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAllByIsDeleted(false)).thenReturn(users);
        ResultActions response = mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", CoreMatchers.is(users.size())))
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(jsonPath("$[0].phoneNumber", CoreMatchers.is(user.getPhoneNumber())));
    }

    @Test
    void updateUser() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        ResultActions response = mockMvc.perform(put("/user/{id}", user.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.firstName", CoreMatchers.is(userUpdateDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", CoreMatchers.is(userUpdateDTO.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", CoreMatchers.is(userUpdateDTO.getPhoneNumber())));
    }

    @Test
    void updateUser_UserNotFound() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(put("/user/{id}", user.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.of(user));
        ResultActions response = mockMvc.perform(delete("/user/{id}", user.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUser_UserNotFound() throws Exception {
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(delete("/user/{id}", user.getId().intValue())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }
}
