package com.qmcr.cal;

public class CalGanZhi {
	
	public String[] TianGan = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
	public String[] DiZhi = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };	
	public String[] GanZhi = {"甲子","乙丑","丙寅","丁卯","戊辰","己巳","庚午","辛未","壬申","癸酉","甲戌","乙亥","丙子","丁丑","戊寅","己卯","庚辰","辛巳","壬午","癸未","甲申","乙酉","丙戌","丁亥","戊子","己丑","庚寅","辛卯","壬辰","癸巳","甲午","乙未","丙申","丁酉","戊戌","己亥","庚子","辛丑","壬寅","癸卯","甲辰","乙巳","丙午","丁未","戊申","己酉","庚戌","辛亥","壬子","癸丑","甲寅","乙卯","丙辰","丁巳","戊午","己未","庚申","辛酉","壬戌","癸亥"};
	public String[] JieQi = {"大雪","冬至", "小寒", "大寒", "立春", "雨水", "惊蛰" , "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪"};
	/**
	 * 通过元素来返回数组下标
	 * @param array
	 * @param item
	 * @return
	 */
	private int getItemByArrayAndIndex(String[] array, String item) {
		int index = 0;
		for(int i=0;i<array.length;i++) {
			if(array[i] == item) {
				index = i;
				break;
			}
		}
		return index;
	}
	/**
	 * 通过地支名来获得地支的顺序
	 * @param name 地支名
	 * @return 地支顺序
	 */
	public int getDiZhiOrderByName(String name) {
		return this.getItemByArrayAndIndex(DiZhi, name)+1;
	}	
	/**
	 * 通过天干名来获得天干的顺序
	 * @param name 天干名
	 * @return 天干顺序
	 */
	public int getTianGanOrderByName(String name) {
		return this.getItemByArrayAndIndex(TianGan, name)+1;
	}

	/**
	 * 通过地支顺序来获得地支名
	 * @param order 地支顺序
	 * @return 地支名
	 */
	public String getDiZhiNameByOrder(int order) {
		return DiZhi[order-1];
	}

	/**
	 * 通过天干顺序来获得天干名
	 * @param order 天干顺序
	 * @return 天干名
	 */
	public String getTianGanNameByOrder(int order) {
		return TianGan[order-1];
	}
	/**
	 * 通过干支顺序来获得干支名
	 * @param order 干支顺序
	 * @return 干支名
	 */
	public String getGanZhiByOrder(int order) {
		String tianGan = this.getTianGanNameByOrder(order%10==0?10:order%10);
		String diZhi = this.getDiZhiNameByOrder(order%12==0?12:order%12);
		return tianGan + diZhi;
	}
	/**
	 * 通过干支名来获得干支的顺序
	 * @param name 干支名
	 */
	public int getGanZhiOrderByName(String name) {
		return this.getItemByArrayAndIndex(GanZhi, name)+1;
	}
	/**
	 * 通过节气名来获得节气的顺序
	 * @param name 节气名
	 */
	public int getSolarItemOrderByName(String name) {
		return this.getItemByArrayAndIndex(JieQi, name)+1;
	}
	/**
	 * 通过节气名来获得月地支
	 * @return 月地支
	 */
	public String getMonthDiZhiBySolarItem(String solarItem) {
		int solarItemOrder = getSolarItemOrderByName(solarItem);
		int index = solarItemOrder/2;
		if(solarItemOrder%2!=0) {
			index = (solarItemOrder+1)/2;
		}
		return getDiZhiNameByOrder(index);
	}
	public Object reSet(Object object, Object[] objs) {//未知错误
		for(Object obj: objs) {
			if(object.equals(obj)) {
				object = obj;
			}
		}
		return object;
	}
}