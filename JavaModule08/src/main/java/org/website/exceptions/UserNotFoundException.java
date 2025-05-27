package org.website.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String login) {
        super("User with login \"" + login + "\" was not found");
    }
}
