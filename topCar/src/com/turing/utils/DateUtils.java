package com.turing.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  @desc 日期工具处理类
* 2022年1月6日     kangyalin           v1.0.0               修改原因
 */
public class DateUtils {
	public static void main(String[] args) throws ParseException {
		String dateStr = "2022-01-12";
		Date now=new Date();//获取当前的日期
		SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("dateStr2"+dFormat.format(now));
		System.out.println("两个日期之间的差距:"+differentDaysByMillisecond(dateStr,dFormat.format(now)));
	}
	public  static  int  differentDaysByMillisecond(String dateStr1,String dateStr2) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date fDate=format.parse(dateStr1);
		Date oDate=format.parse(dateStr2);
		
		//初始化日历工具类
		Calendar aCalendar=Calendar.getInstance();
		//设置开始的日期
		aCalendar.setTime(fDate);
		int day1=aCalendar.get(Calendar.DAY_OF_YEAR);
		//设置结束的日期
		aCalendar.setTime(oDate);
		int day2=aCalendar.get(Calendar.DAY_OF_YEAR);
		int days=day2-day1;
		
		return days;
	}

}
