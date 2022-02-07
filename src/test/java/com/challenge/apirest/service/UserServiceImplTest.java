package com.challenge.apirest.service;

import com.challenge.apirest.model.ERoles;
import com.challenge.apirest.model.Role;
import com.challenge.apirest.repository.RoleRepository;
import com.challenge.apirest.repository.UserRepository;
import com.challenge.apirest.request.LoginRequest;
import com.challenge.apirest.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RequestHistoryService requestHistoryService;

    @MockBean
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private SignupRequest signupRequest;

    private LoginRequest loginRequest;



    @BeforeEach
    public void setUp(){
        request.setMethod("POST");
        request.setServletPath("/test");

        Set<String> roles = new HashSet<>();
        roles.add("user");
        roles.add("admin");

        Role roleUser = Role.builder().name(ERoles.ROLE_USER).build();
        Role roleAdmin = Role.builder().name(ERoles.ROLE_ADMIN).build();

        Collection collectionAuth = new ArrayList();

        GrantedAuthority authority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "auth";
            }
        };

        collectionAuth.add(authority);
        UserDetailsImpl userDetails = new UserDetailsImpl(1L,"test"
                ,"test@gmail.com","test", collectionAuth);

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,"test");

        signupRequest = SignupRequest.builder()
                .email("test@gmail.com")
                .username("test")
                .password("test")
                .role(roles)
                .build();

        loginRequest = LoginRequest.builder()
                .username("test")
                .password("test")
                .build();

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(roleRepository.findByName(ERoles.ROLE_ADMIN)).thenReturn(Optional.of(roleAdmin));
        when(roleRepository.findByName(ERoles.ROLE_USER)).thenReturn(Optional.of(roleUser));
        when(authenticationManager.authenticate(any())).thenReturn(auth);

    }

    @Test
    public void shouldOkRegisterUser() {
        assertThat(service.registerUser(signupRequest, request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldBadRequestRegisterUserByUsernameExist() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        assertThat(service.registerUser(signupRequest, request).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldBadRequestRegisterUserByEmailExist() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThat(service.registerUser(signupRequest, request).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void shouldOkLogin() {
        assertThat(service.loginUser(loginRequest, request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }



}
