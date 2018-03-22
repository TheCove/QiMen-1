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
	public List<Map<String, String>> getSolarTermListByYear(int y) { // 节气使计算范例,y是年分,这是个测试函数
		double jd = 365.2422 * (y - 2000), q;
		String s1;
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 24; i++) {
			Map<String, String> mm = new HashMap<String, String>();
			q = jiaoCal(jd + i * 15.2, i * 15, 0);
			q = q + J2000 + (double) 8 / 24; // 计算第i个节气(i=0是春分),结果转为北京时
			setFromJD(q, true);
			s1 = toStr(); // 将儒略日转成世界时
			mm.put("SolarItem", jqB[i]);
			mm.put("Time", s1.trim());
			ls.add(mm);
		}
		return ls;
	}

	public String getSolarItemByTime(String time) {// 通过时间来查询节气
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

	public String getTimeBySolarItem(String solarItem, int year) {// 通过节气来查询时间
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
	 * 获得某一年的立春的格式化时间
	 * 
	 * @param Year
	 * @return 格式化时间
	 */
	public String getLiChunTime(int Year) {
		return getTimeBySolarItem("立春", Year);
	}

	/**
	 * 获得某一年的立春的时间戳
	 * 
	 * @param Year
	 *            (i.e.:2000)
	 * @return 时间戳
	 */
	public long getLiChunTimeStamp(int Year) {
		String time = getLiChunTime(Year);
		return dateToStamp(time);
	}

	/**
	 * 将格式化的时间转换为时间戳
	 * @param s 格式化的时间
	 * @return timeStamp 时间戳
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
	 * 将时间戳转换为格式化的时间
	 * @param timeStamp 时间戳
	 * @return 格式化的时间
	 */
	public static String stampToFormat(Long timeStamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timeStamp);
		return simpleDateFormat.format(date);
	}

	/**
	 * 日期转格式化的字符串 "yyyy-MM-dd HH:mm:ss" *
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
