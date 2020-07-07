/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interview.template.exceptions;

/**
 *
 * @author Senthil
 */
public class UserAlreadyExitException extends Exception{
    
    public UserAlreadyExitException(String message) {
        super(message);
    }
    
}
