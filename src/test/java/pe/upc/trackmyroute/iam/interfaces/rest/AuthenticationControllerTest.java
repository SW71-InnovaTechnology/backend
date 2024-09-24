package pe.upc.trackmyroute.iam.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;
import pe.upc.trackmyroute.iam.domain.model.commands.SignInCommand;
import pe.upc.trackmyroute.iam.domain.model.commands.SignUpCommand;
import pe.upc.trackmyroute.iam.domain.model.entities.Role;
import pe.upc.trackmyroute.iam.domain.model.valueobjects.Roles;
import pe.upc.trackmyroute.iam.domain.services.UserCommandService;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.SignInResource;
import pe.upc.trackmyroute.iam.interfaces.rest.resources.SignUpResource;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserCommandService userCommandService;

    private User user;
    private SignInResource signInResource;
    private SignUpResource signUpResource;

    @BeforeEach
    void setUp() {
        user = new User("admin", "admin123", List.of(new Role(Roles.ROLE_USER)));
        user.setId(1L);

        // Prepare sign-in and sign-up resources
        signInResource = new SignInResource(user.getUsername(), user.getPassword());
        signUpResource = new SignUpResource(user.getUsername(), user.getPassword(), List.of("ROLE_USER"));
    }

    @Test
    void signIn() throws Exception {
        var authenticatedUser = ImmutablePair.of(user, "expectedToken");
        when(userCommandService.handle(any(SignInCommand.class)))
                .thenReturn(Optional.of(authenticatedUser));

        // Act & Assert
        mockMvc.perform(post("/api/v1/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.token").value("expectedToken"));
    }

    @Test
    void signUp() throws Exception{
        // Arrange
        when(userCommandService.handle(any(SignUpCommand.class)))
                .thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(post("/api/v1/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpResource)))
                .andExpect(status().isBadRequest());
    }
}