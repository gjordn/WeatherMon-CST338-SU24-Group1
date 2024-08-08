/**
 * Gerek G. Jordan
 * CST338 Software Design Summer 24
 *https://github.com/gjordn/WeatherMon-CST338-SU24-Group1.git
 */
package com.example.weathermon;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdminActivityTest {

    private AdminActivity adminActivity;

    @Before
    public void setUp() {
        adminActivity = mock(AdminActivity.class);
    }

    @After
    public void tearDown() {
        adminActivity = null;
    }

    @Test
    public void testCreateUserButtonClick() {
        Button createUserButton = mock(Button.class);

        when(adminActivity.findViewById(R.id.createUserButton)).thenReturn(createUserButton);

        createUserButton.performClick();

        assertEquals(View.VISIBLE, createUserButton.getVisibility());
    }

    @Test
    public void testDeleteUserButtonClick() {
        Button deleteUserButton = mock(Button.class);

        when(adminActivity.findViewById(R.id.deleteUserButton)).thenReturn(deleteUserButton);

        deleteUserButton.performClick();

        assertEquals(View.VISIBLE, deleteUserButton.getVisibility());
    }

    @Test
    public void testMakeAdminButtonClick() {
        Button makeAdminButton = mock(Button.class);

        when(adminActivity.findViewById(R.id.makeAdminButton)).thenReturn(makeAdminButton);

        makeAdminButton.performClick();

        assertEquals(View.VISIBLE, makeAdminButton.getVisibility());
    }

    @Test
    public void testChangePasswordButtonClick() {
        Button changePasswordButton = mock(Button.class);

        when(adminActivity.findViewById(R.id.changePasswordButton)).thenReturn(changePasswordButton);

        changePasswordButton.performClick();

        assertEquals(View.VISIBLE, changePasswordButton.getVisibility());
    }



}
