package com.qmcr.cal;

/**
 * @author http://blog.csdn.net/morixinguan/article/details/52229876 *
 */
public class BetweenDays {
    //判断闰年平年  
    public static boolean isLeap(int y){  
        if((y % 4 == 0 && y % 100 != 0) || y % 400 == 0)  
            return true ;  
        return false ;  
    }  
    //判断一年已经过了多少天  
    public static int GetDay(int y , int m , int d){  
        @SuppressWarnings("unused")
		int sum = 0 ;  
        switch(m){  
        case 12 : sum += 31 ;  
        case 11 : sum += 30 ;   
        case 10 : sum += 31 ;   
        case 9  : sum += 30 ;   
        case 8  : sum += 31 ;   
        case 7  : sum += 31 ;   
        case 6  : sum += 30 ;   
        case 5  : sum += 31 ;   
        case 4  : sum += 30 ;   
        case 3  : sum += 31 ;   
        case 2  :   
                if(isLeap(y) == true)  
                    sum += 29 ;   
                else   
                    sum += 28 ;   
        case 1  : sum += 31 ;  
        sum += d ;  
        }  
        return d ;  
    }  
    //判断一年还剩下多少天  
    public static int OthearDay(int y , int m , int d)  
    {  
        if(isLeap(y) == true)  
           return 366 - GetDay(y , m , d);  
        return 365 - GetDay(y, m, d) ;  
    }  
    //判断两个日期相差的天数  
    public static int Caluater_date_sub_day(int y1,int m1,int d1,int y2,int m2 ,int d2)  
    {  
        if(y1 == y2){  
            int day1 = GetDay(y1, m1, d1) - GetDay(y2, m2, d2) ;  
            return abs(day1);  
        }  
        else if(y1 < y2){  
            int sum1 = OthearDay(y1, m1, d1) ;   
            int sum2 = GetDay(y2, m2, d2) ;  
            int sum3 = 0 ;  
            for(int i = y1 + 1 ; i < y2 ; i++){  
                if(isLeap(i))  
                    sum3+=366 ;  
                else   
                    sum3+=365 ;  
            }  
            return sum1+sum2+sum3 ;  
        }else   
        {  
            int sum1 = GetDay(y1, m1, d1) ;   
            int sum2 = OthearDay(y2, m2, d2) ;  
            int sum3 = 0 ;  
            for(int i = y2 + 1 ; i < y1 ; i++){  
                if(isLeap(i))  
                    sum3+=366 ;  
                else   
                    sum3+=365 ;  
            }  
            return sum1+sum2+sum3 ;  
        }  
    }  
    //计算一个数的绝对值  
    public static int abs(int num){  
        return num > 0 ? num : -num ;  
    }  
}  