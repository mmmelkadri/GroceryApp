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
public class LoginPresenterTest {

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

    @Test
    public void testCheckOwnerPasswordPasswordCorrect() {
        when(view.getUsername()).thenReturn("Apol");
        when(view.getPassword()).thenReturn("Appletest");
        when(model.fetch_owner_password("Apol")).thenReturn("Appletest");

        LoginPresenter presenter = new LoginPresenter(model, view);

        assertTrue(presenter.checkOwnerPassword());
    }

    @Test
    public void testCheckOwnerPasswordEmptyPassword() {
        when(view.getUsername()).thenReturn("Apol");
        when(view.getPassword()).thenReturn("");

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkOwnerPassword();
        verify(view).toastError("Missing password, please complete all the required fields");
    }

    @Test
    public void testCheckOwnerPasswordWrongPassword() {
        when(view.getUsername()).thenReturn("Apol");
        when(view.getPassword()).thenReturn("apple");
        when(model.fetch_owner_password("Apol")).thenReturn("Appletest");

        LoginPresenter presenter = new LoginPresenter(model, view);

        presenter.checkOwnerPassword();
        verify(view).toastError("Invalid Password");
    }
}