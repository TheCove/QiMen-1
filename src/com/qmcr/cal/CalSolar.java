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
	 * ���ĳ���
	 * 
	 * @return ���
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
	 * ���ĳ��֧
	 * 
	 * @return ��֧
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
	 * ���ĳ���֧
	 * 
	 * @return ���֧
	 */
	public String getYearGanZhi() {
		return this.getYearTianGan() + this.getYearDiZhi();
	}

	/**
	 * ���ĳ�¸�֧
	 * 
	 * @return �¸�֧
	 */
	public String getMonthGanZhi() {
		// ͨ���жϵ�ǰ���������֧
		// ͨ����ɺ���֧����¸�
		return this.getMonthTianGan() + this.getMonthDiZhi();
	}

	/**
	 * ���ĳ�¸�
	 * 
	 * @return �¸�
	 */
	private String getMonthTianGan() {
		// �廢��Ԫ
		// �׼�֮������ף��Ҹ�֮����Ϊͷ
		// �����ض�Ѱ���� ��������λ˳����
		// �������η��� ������֮�Ϻ�׷��
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
	 * ���ĳ��֧
	 * 
	 * @return ��֧
	 */
	private String getMonthDiZhi() {
		String solarItem = cal.getSolarItem();// ��ý�����
		return super.getMonthDiZhiBySolarItem(solarItem);// ͨ������ȷ����֧
	}

	/**
	 * ����ո�֧
	 * 
	 * @return �ո�֧
	 */
	public String getDayGanZhi() {
		int distanceDay = cal.getDistanceDay();
		// ��������1900/1/1�Ƚϣ���þ��������
		// 1900/1/1Ϊ������
		int indexOfDayTianGan = (distanceDay + 1) % 10;
		int indexOfDayDiZhi = (distanceDay + 11) % 12;
		indexOfDayTianGan = indexOfDayTianGan == 0 ? 10 : indexOfDayTianGan;
		indexOfDayDiZhi = indexOfDayDiZhi == 0 ? 12 : indexOfDayDiZhi;
		String dayTianGan = super.getTianGanNameByOrder(indexOfDayTianGan);
		String dayDiZhi = super.getDiZhiNameByOrder(indexOfDayDiZhi);
		return dayTianGan + dayDiZhi;
	}

	/**
	 * ����ո�
	 * 
	 * @return �ո�
	 */
	public String getDayTianGan() {
		return this.getDayGanZhi().substring(0, 1);
	}

	/**
	 * �����֧
	 * 
	 * @return ��֧
	 */
	public String getDayDiZhi() {
		return this.getDayGanZhi().substring(1, 2);
	}

	/**
	 * ���ʱ��֧
	 * 
	 * @return ʱ��֧
	 */
	public String getHourGanZhi() {
		// ͨ���жϵ�ǰʱ�����ʱ֧
		// ͨ���ոɺ�ʱ֧���ʱ��
		return this.getHourTianGan() + this.getHourDiZhi();
	}

	/**
	 * ���ʱ��
	 * 
	 * @return ʱ��
	 */
	private String getHourTianGan() {
		// �����Ԫ
		// �׼����Ӽף��Ҹ��Ǳ�����
		// ���������𣬶��ɾӸ��ӣ�
		// ����ںη�����������;��
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
	 * ���ʱ֧
	 * 
	 * @return ʱ֧
	 */
	public String getHourDiZhi() {
		this.Hour = this.Hour % 2 == 0 ? this.Hour : this.Hour + 1;
		int indexOfHourDiZhi = (this.Hour + 2) / 2 % 12;
		return super.getDiZhiNameByOrder(indexOfHourDiZhi);
	}
}
