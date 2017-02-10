package com.jrsoft.fri.dtjk.action;

public class Main {
	private static void getMiddleOne(boolean b, Boolean boo, Boolean[] arr){  
        b = true;  //形参，不会改变原有值  
        boo = new Boolean(true);  //引用变量的直接操作相当于值传递，不会改变原来的引用变量  
        arr[0] = true;  //引用变量的属性的操作，会改变原有引用的属性，相当于传址调用  
    }  
      
    //测试  
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
