package io.nekohasekai.tmicro.tmnet;

public class TMClassDef {

    private static int CONSTRUCTOR = 0;
    public static int TM_NULL = CONSTRUCTOR++;
    public static int TM_OK = CONSTRUCTOR++;
    public static int TM_ERROR = CONSTRUCTOR++;
    public static int TM_RESPONSE = CONSTRUCTOR++;
    public static int TM_INIT_CONNECTION = CONSTRUCTOR++;
    public static int TM_INIT_TEMP = CONSTRUCTOR++;
    public static int TM_INIT_VERIFY = CONSTRUCTOR++;
    public static int TM_CODE_TYPE_MESSAGE = CONSTRUCTOR++;
    public static int TM_CODE_TYPE_SMS = CONSTRUCTOR++;
    public static int TM_CODE_TYPE_CALL = CONSTRUCTOR++;
    public static int TM_CODE_INFO = CONSTRUCTOR++;
    public static int TM_EMAIL_CODE_INFO = CONSTRUCTOR++;
    public static int TM_STATUS_WAIT_PHONE_NUMBER = CONSTRUCTOR++;
    public static int TM_STATUS_WAIT_CODE = CONSTRUCTOR++;
    public static int TM_STATUS_WAIT_REGISTRATION = CONSTRUCTOR++;
    public static int TM_STATUS_WAIT_PASSWORD = CONSTRUCTOR++;
    public static int TM_STATUS_READY = CONSTRUCTOR++;
    public static int TM_STATUS_LOGOUT = CONSTRUCTOR++;

    public static int TM_UPDATE_STATUS = CONSTRUCTOR++;

}
