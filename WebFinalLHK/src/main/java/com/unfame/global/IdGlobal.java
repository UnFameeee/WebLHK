package com.unfame.global;

public class IdGlobal {
    public static int UserId = -1;
    public static String Role = "";


    public static int PageStart = 0;
    public static int PageEnd = 0;
    public static int PageNumber = 1;

    public static String searchValue = "";
    public static String alertSuccess = "";
    public static boolean searchForm = false;
    public static void Reset() {
        alertSuccess = "";
        searchForm = false;
    }
}
