package com.app.housing_association.common.utils;

public interface IValidation {

    //*******************************************************
    //                  MESSAGES
    //*******************************************************
    String USER_NULL_VALIDATION ="User cannot be null";
    String USER_FIRSTNAME_VALIDATION ="Firstname cannot be null or blank";
    String USER_LASTNAME_VALIDATION ="Lastname cannot be null or blank";
    String USER_PHONE_NUMBER_VALIDATION ="Phone number cannot be null or blank";
    String USER_USERNAME_BLANK ="User's username cannot be null or blank";
    String USER_EMAIL_BLANK="User's email cannot be blank or null";
    String USER_EMAIL_SYNTAX_ERROR = "Incorrect syntax of the email address";
    String USER_PHONE_SYNTAX_ERROR = "Incorrect syntax of the phone number";
    String PASSWORD_NULL_VALIDATION ="Password cannot be blank or null";


    String CONTRACT_FLAT_VALIDATION ="Flat has still active contract";
    String CONTRACT_USER_VALIDATION ="User has still active contract";

    String FLAT_NULL_VALIDATION ="Flat cannot be null";
    String FLAT_NOT_EXIST = "Provide flat id not exist";
    String FLAT_DATA_NULL = "Data Flat cannot be null";
    String FLAT_BUILDING_NULL = "Flat building cannot be null";
    String FLAT_ID_BUILDING_NULL = "Building id from flat cannot be null";
    String FLAT_ID_BUILDING_NOT_EXIST = "Building with provide id not exist";
    String FLAT_CAN_NOT_BE_ADD_TO_BUILDING = "Due to constructional reasons, the apartment cannot be added to the " +
            "building or the place is already occupied";

    String REGISTRATION_NULL_VALIDATION = "Data to registration cannot be null";
    String REGISTRATION_ACCOUNT_EXIST = "Account already exist";




    //*******************************************************
    //                  VALIDATION
    //*******************************************************
    String USER_EMAIL_VALIDATION_REGEXP="^([a-z0-9]+)(.[a-z0-9]+)?@([a-z]+).([a-z]{2,3})$";
    String BUILDING_NUMBER_VALIDATION_REGEXP="^[0-9]{1,3}(/[0-9]{1,3})?$";
    String PHONE_NUMBER_VALIDATION_REGEXP="^[0-9]{3}-[0-9]{3}-[0-9]{3}$";

}
