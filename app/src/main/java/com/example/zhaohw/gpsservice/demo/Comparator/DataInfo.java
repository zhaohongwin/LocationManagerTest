package com.example.zhaohw.gpsservice.demo.Comparator;

/**
 *
 * @author Administrator
 * @date 2017/8/4
 */
public class DataInfo {
    
    private int year = 0;
    private int month = 0;
    private int day = 0;
    
    public DataInfo(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getDay() {
        return day;
    }
    
    public void setDay(int day) {
        this.day = day;
    }
}
