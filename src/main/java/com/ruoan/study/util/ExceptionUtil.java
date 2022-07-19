package com.ruoan.study.util;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年05月15日 20:42:00
 */
public class ExceptionUtil {
    //打印异常堆栈信息
    public static String getStackTraceString(Throwable ex) {//(Exception ex) {
        StackTraceElement[] traceElements = ex.getStackTrace();
        StringBuilder traceBuilder = new StringBuilder();
        if (traceElements != null && traceElements.length > 0) {
            for (StackTraceElement traceElement : traceElements) {
                traceBuilder.append("   " + traceElement.toString());
                traceBuilder.append("\n");
            }
        }
        return traceBuilder.toString();
    }

    //构造异常堆栈信息
    public static String buildErrorMessage(Exception ex) {
        String result;
        String stackTrace = getStackTraceString(ex);
        String exceptionType = ex.toString();
        String exceptionMessage = ex.getMessage();
        result = String.format("\n%s : %s\r\n%s", exceptionType, exceptionMessage, stackTrace);
        return result;
    }
}
