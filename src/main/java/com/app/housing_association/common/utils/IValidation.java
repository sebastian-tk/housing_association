package com.app.housing_association.common.utils;

public interface IValidation {

    //*******************************************************
    //                  MESSAGES
    //*******************************************************
    String USER_USERNAME_BLANK ="User's name cannot be blank";
    String USER_EMAIL_BLANK="User's email cannot be blank";
    String USER_EMAIL_SYNTAX_ERROR = "Incorrect syntax of the email address";
    String USER_PASSWORD_EMPTY ="Password cannot be empty";



    //*******************************************************
    //                  VALIDATION
    //*******************************************************
    String USER_EMAIL_VALIDATION_REGEXP="^([a-z0-9]+)(.[a-z0-9]+)?@([a-z]+).([a-z]{2,3})$";

}
