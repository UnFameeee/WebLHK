package com.unfame.Global;

public class Variable {

    private static Variable globalInstance = new Variable();

    public static Variable getInstance() {
        return globalInstance;
    }

    private int PageIndex = 1;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }


}
