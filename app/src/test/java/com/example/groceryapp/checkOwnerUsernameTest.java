package com.example.groceryapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class checkOwnerUsernameTest {

    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;

    @Test
    public void checkOwnerUsernameTest1() {
        // Empty user string

        when(view.getUsername()).thenReturn("");

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkOwnerUsername();

        verify(view).toastError("Missing username, please complete all the required fields");
    }

    @Test
    public void checkOwnerUsernameTest2() {
        // Username exists in data base

        when(view.getUsername()).thenReturn("username");

        when(model.ownerExists("username")).thenReturn(true);

        LoginPresenter presenter = new LoginPresenter(model, view);

        assertEquals(presenter.checkOwnerUsername(), true);
    }

    @Test
    public void checkOwnerUsernameTest3() {
        // Username doesn't exist in data base

        when(view.getUsername()).thenReturn("username");

        when(model.ownerExists("username")).thenReturn(false);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkOwnerUsername();

        verify(view).toastError("Invalid Username");
    }
}

