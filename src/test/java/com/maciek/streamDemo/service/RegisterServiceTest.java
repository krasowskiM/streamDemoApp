package com.maciek.streamDemo.service;

import com.maciek.streamDemo.RegisterStatus;
import com.maciek.streamDemo.persistence.repo.UserRepository;
import com.maciek.streamDemo.request.RegisterRequest;
import com.maciek.streamDemo.response.RegisterResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterServiceTest {
    private static final RegisterRequest INVALID_DATA_REQUEST = new RegisterRequest("mail@mail.com", "123", "456");
    private static final RegisterRequest VALID_DATA_REQUEST = new RegisterRequest("mail@mail.com", "123", "123");

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegisterService registerService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldReturnRegisterStatusOkWhenPasswordsAreEqual() {
        //when
        RegisterResponse register = registerService.register(VALID_DATA_REQUEST);
        RegisterStatus registerStatus = register.getRegisterStatus();
        //then
        assertEquals(RegisterStatus.OK, registerStatus);
    }

    @Test
    public void shouldReturnRegisterStatusPasswordsNotEqualWhenPasswordsAreNotEqual() {
        //when
        RegisterResponse register = registerService.register(INVALID_DATA_REQUEST);
        RegisterStatus registerStatus = register.getRegisterStatus();
        //then
        assertEquals(RegisterStatus.PASSWORDS_NOT_EQUAL, registerStatus);
    }

}