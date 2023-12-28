package com.example.delivery;

import org.jetbrains.annotations.NotNull;

public class Constants {
    public static final long CONNECT_TIMEOUT = 60 * 1000;

    public static final long READ_TIMEOUT = 60 * 1000;

    public static final long WRITE_TIMEOUT = 60 * 1000;

    public static final String BASE_URL = "https://sandbox.safaricom.co.ke/";


    public static final String BUSINESS_SHORT_CODE = "174379";
    public static final String TRANSACTION_TYPE = "CustomerPayBillOnline";
    public static final String PASSKEY = ""; //original passkey
    public static final String PARTYB = "600000"; //same as business shortcode above
    public static final String CALLBACKURL = "https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest";

    //public static final String CALLBACKURL = "https://intelcx.com";

    //You should get these values from the earlier post Part 1
}
