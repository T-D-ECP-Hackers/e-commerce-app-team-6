package org.global.ecp.hackathon.app;

import org.global.ecp.hackathon.app.user.User;

public class TestConstants {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email@email.com";
    public static final String INCORRECT_USERNAME = "incorrect_username";
    public static final User USER = User.builder().username(USERNAME).password(PASSWORD).email(EMAIL).build();
}
