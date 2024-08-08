package com.example.weathermon;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;

public class LoginActivityTest {

    private LoginActivity loginActivity;

    @Before
    public void setUp() {
        loginActivity = mock(LoginActivity.class);
    }

    @After
    public void tearDown() {
        loginActivity = null;
    }
}
