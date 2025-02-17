package org.example.message.property;

public class Constants {

    // TAGS
    public static final  String START = "02";
    public static final  String END = "03";
    public static final  String KERNEL = "9F2A";
    public static final  String PAN = "5A";
    public static final  String AMOUNT = "9F02";
    public static final  String CURRENCY = "5F2A";

    // Kernel
    public static final  String UNKNOWN_KERNEL = "unknown";

    // Tag Length
    public static int START_TAG_LENGTH = 2;
    public static int END_TAG_LENGTH = 2;
    public static int MESSAGE_LENGTH = 2;

    // PAN Length
    public static int AMEX_LENGTH = 15;
    public static int DEFAULT_LENGTH = 16;
}
