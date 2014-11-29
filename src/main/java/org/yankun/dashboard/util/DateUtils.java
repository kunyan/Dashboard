package org.yankun.dashboard.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class DateUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date getThisYearFirstDay(){
		try {
			return sdf.parse(DateTime.now().dayOfYear().withMinimumValue().toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date getNextYearFirstDay(){
		try {
			return sdf.parse(DateTime.now().dayOfYear().withMaximumValue().plusDays(1).toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getLastMonthFirstDay(){
		try {
			return sdf.parse(DateTime.now().minusMonths(1).dayOfMonth().withMinimumValue().toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date getLastMonthLastDay(){
		try {
			return sdf.parse(DateTime.now().minusMonths(1).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getLastWeekFirstDay(){
		try {
			return sdf.parse(DateTime.now().minusWeeks(1).dayOfWeek().withMinimumValue().toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getLastWeekLastDay(){
		try {
			return sdf.parse(DateTime.now().minusWeeks(1).dayOfWeek().withMaximumValue().toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getSystemFirstDay() {
		return new Date(0);
	}
	
	public static Date getYesterday() {
		try {
			return sdf.parse(DateTime.now().minusDays(1).toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getToday() {
		return DateTime.now().toDate();
	}
	
	public static Date getTomorrow() {
		try {
			return sdf.parse(DateTime.now().plusDays(1).toString("yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
