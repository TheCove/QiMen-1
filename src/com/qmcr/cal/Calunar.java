package com.qmcr.cal;

import java.util.Calendar;
import java.util.Date;

public class Calunar extends LunarSolar{
	public int Year;
	public int Month;
	public int Day;
	public int Hour;
	public int Minute;
	public int Second;
	public String DateTime;
	private Calendar cal;
	
	public Calunar() {
		cal = Calendar.getInstance();
		cal.setTime(new Date());
		this.Year = cal.get(Calendar.YEAR);
		this.Month = cal.get(Calendar.MONTH) + 1;
		this.Day = cal.get(Calendar.DAY_OF_MONTH);
		this.Hour = cal.get(Calendar.HOUR_OF_DAY);
		this.Minute = cal.get(Calendar.MINUTE);
		this.Second = cal.get(Calendar.SECOND);
		this.DateTime = this.Year+"-"+this.Month+"-"+this.Day+" "+this.Hour+":"+this.Minute+":"+this.Second;
	}
	public void setYear(int year) {
		this.Year = year;
	}
	public void setMonth(int month) {
		this.Month = month;
	}
	public void setDay(int day) {
		this.Day = day;
	}
	public void setHour(int hour) {
		this.Hour = hour;
	}
	public void setMinute(int minute) {
		this.Minute = minute;
	}
	public void setSecond(int second) {
		this.Second = second;
	}
	public int getYear() {
		return Year;
	}
	public int getMonth() {
		return Month;
	}
	public int getDay() {
		return Day;
	}
	public int getHour() {
		return Hour;
	}
	public int getMinute() {
		return Minute;
	}
	public int getSecond() {
		return Second;
	}
	/**
	 * 获得格式化的日期时间
	 * @return
	 */
	public String getDateTime() {
		this.DateTime = this.Year+"-"+this.Month+"-"+this.Day+" "+this.Hour+":"+this.Minute+":"+this.Second;
		return super.stampToFormat(this.getDateTimeStamp());
		
	}
	/**
	 * 获得给定时间的时间戳
	 * @return
	 */
	public Long getDateTimeStamp() {
		return super.dateToStamp(this.DateTime);
	}
	/**
	 * 获得给定时间的节气名
	 * @return
	 */
	public String getSolarItem() {
		this.DateTime = this.getDateTime();
		return super.getSolarItemByTime(DateTime);
	}
	/**
	 * 获得给定时间的所属年份的立春点
	 * @return
	 */
	public Long getLiChunTimeStamp() {
		return super.getLiChunTimeStamp(this.Year);
	}
	/**
	 * 获得给定时间的所属年份的立春点的格式化时间
	 * @return
	 */
	public String getLiChunTime() {
		return super.getLiChunTime(this.Year);
	}
	public int getDistanceDay() {
		int y1 = this.getYear();
		int m1 = this.getMonth();
		int d1 = this.getDay();
		int y2 = 1900;
		int m2 = 1;
		int d2 = 1;
		int distanceDay = BetweenDays.Caluater_date_sub_day(y1, m1, d1, y2, m2, d2);
		return distanceDay;
	}
}
