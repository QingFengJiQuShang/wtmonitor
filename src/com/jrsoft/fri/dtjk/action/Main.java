package com.jrsoft.fri.dtjk.action;

public class Main {
	private static void getMiddleOne(boolean b, Boolean boo, Boolean[] arr){  
        b = true;  //�βΣ�����ı�ԭ��ֵ  
        boo = new Boolean(true);  //���ñ�����ֱ�Ӳ����൱��ֵ���ݣ�����ı�ԭ�������ñ���  
        arr[0] = true;  //���ñ��������ԵĲ�������ı�ԭ�����õ����ԣ��൱�ڴ�ַ����  
    }  
      
    //����  
    public static void main(String[] args) {  
        boolean b = false;  
        Boolean boo = new Boolean(false);  
        Boolean[] arr = new Boolean[]{false};  
  
        getMiddleOne(b, boo, arr);  
          
        System.out.println(b);    
        System.out.println(boo.toString());  
        System.out.println(arr[0]);  
  
        /** 
         * output: 
         *      false 
         *      false 
         *      true 
         */  
    }  
}
