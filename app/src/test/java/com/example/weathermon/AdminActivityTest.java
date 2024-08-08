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

}
