package com.app.housing_association.common.utils;

public interface IValidation {

    //*******************************************************
    //                  MESSAGES
    //*******************************************************
    String CONTRACT_FLAT_VALIDATION ="Flat has still active contract";
    String CONTRACT_USER_VALIDATION ="User has still active contract";

    String FLAT_NULL_VALIDATION ="Flat cannot be null";
    String FLAT_ID_NULL ="Flat ID cannot be null";
    String FLAT_NOT_EXIST = "Provide flat id not exist";
    String FLAT_DATA_NULL = "Data Flat cannot be null";
    String FLAT_BUILDING_NULL = "Flat building cannot be null";
    String FLAT_ID_BUILDING_NULL = "Building id from flat cannot be null";
    String FLAT_ID_BUILDING_NOT_EXIST = "Building with provide id not exist";
    String FLAT_CAN_NOT_BE_ADD_TO_BUILDING = "Due to constructional reasons, the apartment cannot be added to the " +
            "building or the place is already occupied";

    String FAULT_TITLE_VALIDATION = "Title in fault cannot be null or empty";
    String FAULT_DESCRIPTION_VALIDATION = "Description in fault cannot be null or empty";
    String FAULT_IMAGE_PATH_VALIDATION = "Image path in fault cannot be null or empty";
    String FAULT_BUILDING_VALIDATION = "Building in fault cannot be null";
    String FAULT_VALIDATION = "Fault cannot be null";
    String FAULT_WITH_ID_NOT_EXIST = "Fault to update not exist with id:";

    String FILE_CREATE_DIRECTORY_ERROR = "Error creating directory with path:";

    String IMAGE_LOG_SAVE_FILE_MESSAGE_ERROR = "Image: {} with id:{} save filed";
    String IMAGE_SIZE_ERROR = "The image size is too large";
    String IMAGE_SAVE_ERROR = "Error saving image with id: {}";
    String IMAGE_REMOVE_ERROR = "Error removing image with path: {}";

    String NOTICE_TITLE_NULL = "Notice title cannot be null";
    String NOTICE_DESCRIPTION_NULL = "Notice description cannot be null";


    String USER_NULL_VALIDATION ="User cannot be null";
    String USER_FIRSTNAME_VALIDATION ="Firstname cannot be null or blank";
    String USER_LASTNAME_VALIDATION ="Lastname cannot be null or blank";
    String USER_PHONE_NUMBER_VALIDATION ="Phone number cannot be null or blank";
    String USER_USERNAME_BLANK ="User's username cannot be null or blank";
    String USER_EMAIL_BLANK="User's email cannot be blank or null";
    String USER_EMAIL_SYNTAX_ERROR = "Incorrect syntax of the email address";
    String USER_PHONE_SYNTAX_ERROR = "Incorrect syntax of the phone number";
    String USER_PASSWORD_NULL_VALIDATION ="Password cannot be blank or null";
    String USER_ID_VALIDATION="Provide ID cannot be null";
    String USER_ID_NOT_EXIST="User with provide id not exist";
    String USER_OLD_PASSWORD_VALIDATION="Provide current password cannot be null";
    String USER_NEW_PASSWORD_VALIDATION="Provide new password cannot be null";
    String USER_PASSWORD_CORRECT="Provide password is not correct";
    String USER_CANNOT_CAST_VOTE="The user has already voted in this vote";
    String USER_AND_VOTES_HAVE_DIFFERENT_BUILDING="User building id and voting building id do not match";
    String USER_WITHOUT_FLAT="User isn't assigned to any flat";

    String PDF_LOG_SAVE_FILE_MESSAGE_ERROR = "PDF: {} with id:{} save filed";
    String PDF_SIZE_ERROR = "The PDF size is too large, max: %d MB";
    String PDF_SAVE_ERROR = "Error saving PDF with id: {}";
    String PDF_REMOVE_ERROR = "Error removing PDF with path: {}";
    String PDF_READ_ERROR = "Error during read PDF: {} from directory, message: {}";

    String REGISTRATION_NULL_VALIDATION = "Data to registration cannot be null";
    String REGISTRATION_ACCOUNT_EXIST = "Account already exist";

    String VOTE_VALIDATION = "Vote cannot be null";
    String VOTE_TITLE_VALIDATION = "Title in vote cannot be null or empty";
    String VOTE_DESCRIPTION_VALIDATION = "Description in vote cannot be null or empty";

    //*******************************************************
    //                  VALIDATION
    //*******************************************************
    String USER_EMAIL_VALIDATION_REGEXP="^([a-z0-9]+)(.[a-z0-9]+)?@([a-z]+).([a-z]{2,3})$";
    String BUILDING_NUMBER_VALIDATION_REGEXP="^[0-9]{1,3}(/[0-9]{1,3})?$";
    String PHONE_NUMBER_VALIDATION_REGEXP="^[0-9]{3}-[0-9]{3}-[0-9]{3}$";

}
