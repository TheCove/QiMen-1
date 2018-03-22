package com.qmcr.cal;

public class CalGanZhi {
	
	public String[] TianGan = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };
	public String[] DiZhi = { "��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��" };	
	public String[] GanZhi = {"����","�ҳ�","����","��î","�쳽","����","����","��δ","����","����","����","�Һ�","����","����","����","��î","����","����","����","��δ","����","����","����","����","����","����","����","��î","�ɳ�","����","����","��δ","����","����","����","����","����","����","����","��î","�׳�","����","����","��δ","����","����","����","����","����","���","����","��î","����","����","����","��δ","����","����","����","�ﺥ"};
	public String[] JieQi = {"��ѩ","����", "С��", "��", "����", "��ˮ", "����" , "����", "����", "����", "����", "С��", "â��", "����", "С��", "����", "����", "����", "��¶", "���", "��¶", "˪��", "����", "Сѩ"};
	/**
	 * ͨ��Ԫ�������������±�
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
	 * ͨ����֧������õ�֧��˳��
	 * @param name ��֧��
	 * @return ��֧˳��
	 */
	public int getDiZhiOrderByName(String name) {
		return this.getItemByArrayAndIndex(DiZhi, name)+1;
	}	
	/**
	 * ͨ��������������ɵ�˳��
	 * @param name �����
	 * @return ���˳��
	 */
	public int getTianGanOrderByName(String name) {
		return this.getItemByArrayAndIndex(TianGan, name)+1;
	}

	/**
	 * ͨ����֧˳������õ�֧��
	 * @param order ��֧˳��
	 * @return ��֧��
	 */
	public String getDiZhiNameByOrder(int order) {
		return DiZhi[order-1];
	}

	/**
	 * ͨ�����˳������������
	 * @param order ���˳��
	 * @return �����
	 */
	public String getTianGanNameByOrder(int order) {
		return TianGan[order-1];
	}
	/**
	 * ͨ����֧˳������ø�֧��
	 * @param order ��֧˳��
	 * @return ��֧��
	 */
	public String getGanZhiByOrder(int order) {
		String tianGan = this.getTianGanNameByOrder(order%10==0?10:order%10);
		String diZhi = this.getDiZhiNameByOrder(order%12==0?12:order%12);
		return tianGan + diZhi;
	}
	/**
	 * ͨ����֧������ø�֧��˳��
	 * @param name ��֧��
	 */
	public int getGanZhiOrderByName(String name) {
		return this.getItemByArrayAndIndex(GanZhi, name)+1;
	}
	/**
	 * ͨ������������ý�����˳��
	 * @param name ������
	 */
	public int getSolarItemOrderByName(String name) {
		return this.getItemByArrayAndIndex(JieQi, name)+1;
	}
	/**
	 * ͨ��������������µ�֧
	 * @return �µ�֧
	 */
	public String getMonthDiZhiBySolarItem(String solarItem) {
		int solarItemOrder = getSolarItemOrderByName(solarItem);
		int index = solarItemOrder/2;
		if(solarItemOrder%2!=0) {
			index = (solarItemOrder+1)/2;
		}
		return getDiZhiNameByOrder(index);
	}
	public Object reSet(Object object, Object[] objs) {//δ֪����
		for(Object obj: objs) {
			if(object.equals(obj)) {
				object = obj;
			}
		}
		return object;
	}
}