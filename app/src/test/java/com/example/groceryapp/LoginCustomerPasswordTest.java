package com.example.groceryapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Mohamad El Kadri Testing Suite
@RunWith(MockitoJUnitRunner.class)
public class LoginCustomerPasswordTest {

    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;

    @Test
    public void checkCustomerPasswordTest1() {
        // If password is an empty string

        when(view.getUsername()).thenReturn("username");
        when(view.getPassword()).thenReturn("");

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkCustomerPassword();

        verify(view).toastError("Missing password, please complete all the required fields");
    }

    @Test
    public void checkCustomerPasswordTest2() {
        // If password is non-empty, and matches usernames password.

        when(view.getUsername()).thenReturn("username");
        when(view.getPassword()).thenReturn("password");

        when(model.fetch_customer_password("username")).thenReturn("password");

        LoginPresenter presenter = new LoginPresenter(model, view);

        assertEquals(presenter.checkCustomerPassword(), true);
    }

    @Test
    public void checkCustomerPasswordTest3() {
        // If password is non-empty, but does not match usernames password.

        when(view.getUsername()).thenReturn("username");
        when(view.getPassword()).thenReturn("pass");

        when(model.fetch_customer_password("username")).thenReturn("password");

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkCustomerPassword();

        verify(view).toastError("Invalid Password");
    }
}