package com.javarush.task.task22.task2201;

public class ThisUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else
            if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
                System.out.println(getFormattedStringForSecondThread(t, e, string));
            } else {
                System.out.println(getFormattedStringForOtherThread(t, e, string));
            }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        //RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#
        StringBuffer sb = new StringBuffer();
        sb.append(e.getClass().getSimpleName());
        sb.append(" : ");
        sb.append(e.getMessage());
        sb.append(" : ");
        sb.append(t.getName());


        return sb.toString();
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        //java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : TooShortStringSecondThreadException : 2#
        StringBuffer sb = new StringBuffer();
        sb.append(e.getMessage());
        sb.append(" : ");
        sb.append(e.getClass().getSimpleName());
        sb.append(" : ");
        sb.append(t.getName());
        return sb.toString();
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        //1# : TooShortStringFirstThreadException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1
        StringBuffer sb = new StringBuffer();
        sb.append(t.getName());
        sb.append(" : ");
        sb.append(e.getClass().getSimpleName());
        sb.append(" : ");
        sb.append(e.getMessage());
        return sb.toString();
    }
}

