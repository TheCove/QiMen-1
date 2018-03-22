package com.qmcr.cal;
/**
 * @author echec
 * @mail szysec@163.com
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LunarSolar extends SolarTerm {
	public List<Map<String, String>> getSolarTermListByYear(int y) { // ����ʹ���㷶��,y�����,���Ǹ����Ժ���
		double jd = 365.2422 * (y - 2000), q;
		String s1;
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 24; i++) {
			Map<String, String> mm = new HashMap<String, String>();
			q = jiaoCal(jd + i * 15.2, i * 15, 0);
			q = q + J2000 + (double) 8 / 24; // �����i������(i=0�Ǵ���),���תΪ����ʱ
			setFromJD(q, true);
			s1 = toStr(); // ��������ת������ʱ
			mm.put("SolarItem", jqB[i]);
			mm.put("Time", s1.trim());
			ls.add(mm);
		}
		return ls;
	}

	public String getSolarItemByTime(String time) {// ͨ��ʱ������ѯ����
		Long times = dateToStamp(time);
		int year = Integer.parseInt(time.substring(0, 4));
		List<Map<String, String>> ll = getSolarTermListByYear(year - 1);
		ll.addAll(getSolarTermListByYear(year));
		for (int i = 0; i < ll.size(); i++) {
			Long preTime = dateToStamp(ll.get(i).get("Time"));
			Long afterTime = dateToStamp(ll.get(i + 1).get("Time"));
			if (times >= preTime && times <= afterTime) {
				return ll.get(i).get("SolarItem");
			}
		}
		return null;
	}

	public String getTimeBySolarItem(String solarItem, int year) {// ͨ����������ѯʱ��
		List<Map<String, String>> ll = getSolarTermListByYear(year - 1);
		ll.addAll(getSolarTermListByYear(year));
		for (int i = 0; i < ll.size(); i++) {
			if (ll.get(i).get("Time").startsWith("" + year)) {
				if (ll.get(i).get("SolarItem") == solarItem) {
					return ll.get(i).get("Time");
				}

			}
		}
		return null;
	}

	/**
	 * ���ĳһ��������ĸ�ʽ��ʱ��
	 * 
	 * @param Year
	 * @return ��ʽ��ʱ��
	 */
	public String getLiChunTime(int Year) {
		return getTimeBySolarItem("����", Year);
	}

	/**
	 * ���ĳһ���������ʱ���
	 * 
	 * @param Year
	 *            (i.e.:2000)
	 * @return ʱ���
	 */
	public long getLiChunTimeStamp(int Year) {
		String time = getLiChunTime(Year);
		return dateToStamp(time);
	}

	/**
	 * ����ʽ����ʱ��ת��Ϊʱ���
	 * @param s ��ʽ����ʱ��
	 * @return timeStamp ʱ���
	 */
	public static Long dateToStamp(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * ��ʱ���ת��Ϊ��ʽ����ʱ��
	 * @param timeStamp ʱ���
	 * @return ��ʽ����ʱ��
	 */
	public static String stampToFormat(Long timeStamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timeStamp);
		return simpleDateFormat.format(date);
	}

	/**
	 * ����ת��ʽ�����ַ��� "yyyy-MM-dd HH:mm:ss" *
	 * 
	 * @param datetime
	 * @return
	 */
	public String formatTime(Date datetime) {
		String format = "yyyy-MM-dd HH:mm:ss";
		String formattedTime = new SimpleDateFormat(format).format(datetime);
		return formattedTime;
	}
}
