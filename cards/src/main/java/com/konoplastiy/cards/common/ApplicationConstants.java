package com.konoplastiy.cards.common;

import lombok.experimental.UtilityClass;

/**
 * Contains various constants used in the application.
 */
@UtilityClass
public final class ApplicationConstants {

    @UtilityClass
    public class Status {
        public static final String  CREDIT_CARD = "Credit Cards";
        public static final int  NEW_CARD_LIMIT = 1_00_000;
        public static final String  STATUS_201 = "201";
        public static final String  MESSAGE_201 = "Cards created successfully";
        public static final String  STATUS_200 = "200";
        public static final String  MESSAGE_200 = "Request processed successfully";
        public static final String  STATUS_417 = "417";
        public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
        public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    }


}
