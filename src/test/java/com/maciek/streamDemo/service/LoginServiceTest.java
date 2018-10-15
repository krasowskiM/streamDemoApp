package com.maciek.streamDemo.service;

import com.maciek.streamDemo.LoginStatus;
import com.maciek.streamDemo.persistence.entity.User;
import com.maciek.streamDemo.persistence.repo.UserRepository;
import com.maciek.streamDemo.request.LoginRequest;
import com.maciek.streamDemo.response.LoginResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginRequest loginRequest;
    @InjectMocks
    private LoginService loginService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void tryLogUserShouldReturnStatusOkWhenEmailWasSpecifiedInLoginRequest() {
        //given
        String password = "dummy_password";
        String email = "test@test.com";
        when(userRepository.findByEmail(email)).thenReturn(new User().withEmail(email).withPassword(password));
        when(loginRequest.getEmail()).thenReturn(email);
        when(loginRequest.getPassword()).thenReturn(password);

        //when
        LoginResponse loginResponse = loginService.tryLogUser(loginRequest);

        //then
        assertEquals("login response should have status OK", LoginStatus.OK, loginResponse.getLoginStatus());
    }
}