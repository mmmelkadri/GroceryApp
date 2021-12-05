package com.example.groceryapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@RunWith(MockitoJUnitRunner.class)
public class LoginCustomerUsernameTest {

    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;

    @Test
    public void checkCustomerUsernameEmpty() {
        // If username is an empty string

        when(view.getUsername()).thenReturn("");

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkCustomerUsername();

        verify(view).toastError("Missing username, please complete all the required fields");
    }

    @Test
    public void checkCustomerUsernameExists() {
        // If username is valid.

        when(view.getUsername()).thenReturn("username");
        when(model.customerExists("username")).thenReturn(true);

        LoginPresenter presenter = new LoginPresenter(model, view);

        assertEquals(presenter.checkCustomerUsername(), true);
    }

    @Test
    public void checkCustomerUsernameInvalid() {
        // If username is invalid.

        when(view.getUsername()).thenReturn("username1");
        when(model.customerExists("username1")).thenReturn(false);

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkCustomerUsername();

        verify(view).toastError("Invalid Username");
    }
}
