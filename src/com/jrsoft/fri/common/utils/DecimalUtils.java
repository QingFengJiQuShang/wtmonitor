package com.jrsoft.fri.common.utils;

import java.text.DecimalFormat;
import java.util.*;

public class DecimalUtils {

    /**
     * 数字转换
     * @return
     */
	public static String decimal(int number){
    	DecimalFormat format = new DecimalFormat("#,##0.00");
    	return format.format(number);
    } 
 
	 /**
     * 数字转换
     * @return
     */
	public static String decimal(long number){
    	DecimalFormat format = new DecimalFormat("#,##0.00");
    	return format.format(number);
    } 
}
