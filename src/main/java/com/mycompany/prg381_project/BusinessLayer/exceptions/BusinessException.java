package com.mycompany.prg381_project.BusinessLayer.exceptions;

/**
 * Thrown by the BusinessLayer services whenever a business rule or
 * validation check fails (empty required field, insufficient stock,
 * duplicate username, etc). The message is written to be shown directly
 * to the user in a dialog - the UI layer should never need to reword it.
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
