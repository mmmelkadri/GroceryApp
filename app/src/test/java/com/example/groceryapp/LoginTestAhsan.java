package com.example.groceryapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTestAhsan {
    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;

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
        when(model.fetch_owner_password("Apol")).thenReturn("Appletest");

        LoginPresenter presenter = new LoginPresenter(model, view);

        Exception exception = assertThrows(IllegalArgumentException.class, presenter::checkOwnerPassword);
        assertEquals("Missing password, please complete all the required fields", exception.getMessage());
    }

    @Test
    public void testCheckOwnerPasswordWrongPassword() {
        when(view.getUsername()).thenReturn("Apol");
        when(view.getPassword()).thenReturn("apple");
        when(model.fetch_owner_password("Apol")).thenReturn("Appletest");

        LoginPresenter presenter = new LoginPresenter(model, view);

        Exception exception = assertThrows(IllegalArgumentException.class, presenter::checkOwnerPassword);
        assertEquals("Invalid Password", exception.getMessage());
    }
}
