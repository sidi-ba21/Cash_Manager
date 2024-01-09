package com.cashmanager.cash.security;

public class SecurityConstants {

    public static String JWT_SECRET = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDFfct6S23JPm4G";

    public static int JWT_TOKEN_DURATION = 30 * 60 * 1000;
    public static int JWT_REFRESH_DURATION = 180 * 60 * 1000;

}
