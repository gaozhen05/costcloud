package com.njwd.zuul.util;

/**
 * Copyright @ 2010 NANJING WANGDOU SOFTWARE Co. Ltd.
 * All right reserved
 * @author Administrator
 * @date Apr 11, 2011
 * @description 
 */

/**
 * @author Machao
 * @date 11:24:06 AM 
 */
public class ExceptionUtil {

    
    /**
     * 
     * @param e
     */
    public static void printMessage(Exception e) {
        try{
            e.printStackTrace();
            String exceptionName = e.getClass().getSimpleName();
            String message = "系统异常";
            if (exceptionName != null) {
                if (exceptionName.equals("NullPointerException")) {
                    message = "空指针异常";
                } else if (exceptionName.equals("NumberFormatException")) {
                    message = "数值类型转换异常";
                } else if (exceptionName.equals("IndexOutOfBoundsException")) {
                    message = "数组下标越界异常";
                } else if (exceptionName.equals("ArrayIndexOutOfBoundsException")) {
                    message = "列表下标越界异常";
                } else if (exceptionName.equals("ClassCastException")) {
                    message = "对象转换异常";
                } else if (exceptionName.equals("NoSuchElementException")) {
                    message = "找不到指定的元素";
                } else if (exceptionName.equals("IllegalArgumentException")) {
                    message = "非法的参数";
                } else if (exceptionName.equals("IllegalPathStateException")) {
                    message = "非法路径";
                } else if (exceptionName.equals("Exception")) {
                    message = "异常反馈";
                }
            }
            message = e.getMessage() == null ? message : message + ":"
                    + e.getMessage();
            StackTraceElement o=e.getStackTrace()[0];

        }catch(Exception e1){
        	e1.printStackTrace();
        }
    }
}
