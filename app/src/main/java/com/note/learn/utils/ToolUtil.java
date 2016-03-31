package com.note.learn.utils;

/**
 * Created by wanghui on 2016/3/31.
 */
public class ToolUtil {

    public static int getDateNumByMonth(int year, int month) {
        if (month < 1) {
            return 30;
        }
        if (month == 2) {
            return (year % 4 == 0) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    public static int[] getNextMonth(int year, int month) {
        if (month == 12) {
            year = year + 1;
            month = 1;
        }else{
            month = month+1;
        }
        return new int[]{year, month};
    }

    public static int[] getPreMonth(int year,int month){
        if(month == 1){
            year = year -1;
            month = 12;
        }else {
            month = month-1;
        }
        return new int[]{year, month};
    }
}
