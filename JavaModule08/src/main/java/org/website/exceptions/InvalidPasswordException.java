package org.website.exceptions;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String login) {
        super("Incorrect password for user with login \"" + login + "\"");
    }
}
