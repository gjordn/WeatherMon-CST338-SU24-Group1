package com.example.weathermon;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;

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

}
