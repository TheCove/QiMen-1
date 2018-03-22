package com.qmcr.cal;

public class CalSolar extends CalGanZhi {
	private Calunar cal;
	public int Year;
	public int Month;
	public int Day;
	public int Hour;
	public int Minute;
	public int Second;

	public CalSolar() {
		cal = new Calunar();
		Year = cal.getYear();
		Month = cal.getMonth();
		Day = cal.getDay();
		Hour = cal.getHour();
		Minute = cal.getMinute();
		Second = cal.getSecond();
	}

	public void setYear(int year) {
		this.Year = year;
		cal.setYear(this.Year);
	}

	public void setMonth(int month) {
		this.Month = month;
		cal.setMonth(this.Month);
	}

	public void setDay(int day) {
		this.Day = day;
		cal.setDay(this.Day);
	}

	public void setHour(int hour) {
		this.Hour = hour;
		cal.setHour(this.Hour);
	}

	public void setMinute(int minute) {
		this.Minute = minute;
		cal.setMinute(this.Minute);
	}

	public void setSecond(int second) {
		this.Second = second;
		cal.setSecond(this.Second);
	}

	public String getDateTime() {
		return cal.getDateTime();
	}

	public Long getDateTimeStamp() {
		return cal.getDateTimeStamp();
	}

	public Long getLiChunTimeStamp() {
		return cal.getLiChunTimeStamp();
	}

	/**
	 * 获得某年干
	 * 
	 * @return 年干
	 */
	private String getYearTianGan() {
		int xYear = this.Year;
		if (this.getLiChunTimeStamp() > this.getDateTimeStamp()) {
			xYear -= 1;
		}
		int index = (xYear - 3) % 10;
		return super.getTianGanNameByOrder(index == 0 ? 10 : index);
	}

	/**
	 * 获得某年支
	 * 
	 * @return 年支
	 */
	private String getYearDiZhi() {
		int xYear = this.Year;
		if (this.getLiChunTimeStamp() > this.getDateTimeStamp()) {
			xYear -= 1;
		}
		int index = (xYear - 3) % 12;
		return super.getDiZhiNameByOrder(index == 0 ? 12 : index);
	}

	/**
	 * 获得某年干支
	 * 
	 * @return 年干支
	 */
	public String getYearGanZhi() {
		return this.getYearTianGan() + this.getYearDiZhi();
	}

	/**
	 * 获得某月干支
	 * 
	 * @return 月干支
	 */
	public String getMonthGanZhi() {
		// 通过判断当前节气获得月支
		// 通过年干和月支获得月干
		return this.getMonthTianGan() + this.getMonthDiZhi();
	}

	/**
	 * 获得某月干
	 * 
	 * @return 月干
	 */
	private String getMonthTianGan() {
		// 五虎遁元
		// 甲己之年丙作首，乙庚之岁戊为头
		// 丙辛必定寻庚起 ，丁壬壬位顺行流
		// 若问戊癸何方发 ，甲寅之上好追求
		String YearTianGan = this.getYearTianGan();
		int IndexOfYearTianGan = super.getTianGanOrderByName(YearTianGan);
		IndexOfYearTianGan = (IndexOfYearTianGan % 5 * 2 + 1) % 10;
		int differ = super.getDiZhiOrderByName(this.getMonthDiZhi()) - 3;
		if (differ <= 0) {
			differ += 12;
		}
		int IndexOfMonthTianGan = (IndexOfYearTianGan + differ) % 10;
		if (IndexOfMonthTianGan == 0) {
			IndexOfMonthTianGan += 10;
		}
		return super.getTianGanNameByOrder(IndexOfMonthTianGan);
	}

	/**
	 * 获得某月支
	 * 
	 * @return 月支
	 */
	private String getMonthDiZhi() {
		String solarItem = cal.getSolarItem();// 获得节气名
		return super.getMonthDiZhiBySolarItem(solarItem);// 通过节气确定月支
	}

	/**
	 * 获得日干支
	 * 
	 * @return 日干支
	 */
	public String getDayGanZhi() {
		int distanceDay = cal.getDistanceDay();
		// 将日期与1900/1/1比较，获得距离的天数
		// 1900/1/1为甲戌日
		int indexOfDayTianGan = (distanceDay + 1) % 10;
		int indexOfDayDiZhi = (distanceDay + 11) % 12;
		indexOfDayTianGan = indexOfDayTianGan == 0 ? 10 : indexOfDayTianGan;
		indexOfDayDiZhi = indexOfDayDiZhi == 0 ? 12 : indexOfDayDiZhi;
		String dayTianGan = super.getTianGanNameByOrder(indexOfDayTianGan);
		String dayDiZhi = super.getDiZhiNameByOrder(indexOfDayDiZhi);
		return dayTianGan + dayDiZhi;
	}

	/**
	 * 获得日干
	 * 
	 * @return 日干
	 */
	public String getDayTianGan() {
		return this.getDayGanZhi().substring(0, 1);
	}

	/**
	 * 获得日支
	 * 
	 * @return 日支
	 */
	public String getDayDiZhi() {
		return this.getDayGanZhi().substring(1, 2);
	}

	/**
	 * 获得时干支
	 * 
	 * @return 时干支
	 */
	public String getHourGanZhi() {
		// 通过判断当前时辰获得时支
		// 通过日干和时支获得时干
		return this.getHourTianGan() + this.getHourDiZhi();
	}

	/**
	 * 获得时干
	 * 
	 * @return 时干
	 */
	private String getHourTianGan() {
		// 五鼠遁元
		// 甲己还加甲，乙庚是丙初，
		// 丙辛从戊起，丁壬居庚子；
		// 戊癸在何方，壬子是真途。
		String DayTianGan = this.getDayTianGan();
		DayTianGan = (String) super.reSet(DayTianGan, super.TianGan);
		int IndexOfDayTianGan = super.getTianGanOrderByName(DayTianGan);
		IndexOfDayTianGan %= 5;
		if (IndexOfDayTianGan <= 0) {
			IndexOfDayTianGan += 5;
		}
		IndexOfDayTianGan = (IndexOfDayTianGan * 2 - 1) % 10;
		int differ = super.getDiZhiOrderByName(this.getHourDiZhi()) - 1;
		if (differ <= 0) {
			differ += 12;
		}
		int IndexOfHourTianGan = (IndexOfDayTianGan + differ) % 10;
		if (IndexOfHourTianGan <= 0) {
			IndexOfHourTianGan += 10;
		}
		return super.getTianGanNameByOrder(IndexOfHourTianGan);
	}

	/**
	 * 获得时支
	 * 
	 * @return 时支
	 */
	public String getHourDiZhi() {
		this.Hour = this.Hour % 2 == 0 ? this.Hour : this.Hour + 1;
		int indexOfHourDiZhi = (this.Hour + 2) / 2 % 12;
		return super.getDiZhiNameByOrder(indexOfHourDiZhi);
	}
}
