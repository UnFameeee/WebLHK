package com.unfame.global;

public class IdGlobal {
    public static int UserId = -1;
    public static String Role = "";
    public static int PageLIMIT = 0;
    public static boolean maxPage = false;
    public static boolean minPage = false;
    public static String searchValue = "";
    public static String alertSuccess = "";
    public static boolean searchForm = false;
    public static void Reset() {
        alertSuccess = "";
        searchForm = false;
    }
}
