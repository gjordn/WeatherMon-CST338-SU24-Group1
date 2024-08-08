package com.example.weathermon;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void testLoginButtonClick() {
        EditText userNameEditText = mock(EditText.class);
        EditText passwordEditText = mock(EditText.class);
        Button loginButton = mock(Button.class);

        when(loginActivity.findViewById(R.id.userNameLoginEditText)).thenReturn(userNameEditText);
        when(loginActivity.findViewById(R.id.passwordLoginEditText)).thenReturn(passwordEditText);
        when(loginActivity.findViewById(R.id.loginButton)).thenReturn(loginButton);

        Editable mockUserName = mock(Editable.class);
        Editable mockPassword = mock(Editable.class);

        when(userNameEditText.getText()).thenReturn(mockUserName);
        when(passwordEditText.getText()).thenReturn(mockPassword);

        when(mockUserName.toString()).thenReturn("testUser");
        when(mockPassword.toString()).thenReturn("testPassword");

        loginButton.performClick();

        assertEquals("testUser", userNameEditText.getText().toString());
        assertEquals("testPassword", passwordEditText.getText().toString());
    }


}
