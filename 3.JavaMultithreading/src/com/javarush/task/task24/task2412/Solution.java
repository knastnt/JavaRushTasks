package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/
public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"closed {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        //MessageFormat pattform = new MessageFormat("{0} {1}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};

            System.out.println(pattform.format(testArgs));

        }
    }

    public static void sort(List<Stock> list) {
        Comparator compName = new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                return stock1.get("name").toString().compareTo(stock2.get("name").toString());
            }
        };
        Comparator compDate = new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                Calendar cal = Calendar.getInstance();

                cal.setTime((Date)stock1.get("date"));
                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.set(Calendar.MINUTE,0);
                cal.set(Calendar.SECOND,0);
                cal.set(Calendar.MILLISECOND,0);
                Date stock1Date = cal.getTime();

                cal.setTime((Date)stock2.get("date"));
                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.set(Calendar.MINUTE,0);
                cal.set(Calendar.SECOND,0);
                cal.set(Calendar.MILLISECOND,0);
                Date stock2Date = cal.getTime();


                return stock1Date.compareTo(stock2Date);
            }
        };
        Comparator compProfit = new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                double stock1Profit;
                double stock2Profit;
                if(stock1.containsKey("change")){
                    stock1Profit = (double)stock1.get("change");
                }else{
                    stock1Profit = (double)stock1.get("last") - (double)stock1.get("open");
                }
                if(stock2.containsKey("change")){
                    stock2Profit = (double)stock2.get("change");
                }else{
                    stock2Profit = (double)stock2.get("last") - (double)stock2.get("open");
                }

                if (stock1Profit < stock2Profit) return 1;
                if (stock1Profit > stock2Profit) return -1;
                return 0;
            }
        };

        Collections.sort(list, compName.thenComparing(compDate).thenComparing(compProfit));


    }

    public static class Stock extends HashMap {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        /*Date d = getRandomDate();
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", .05, d));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.07, d));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", .13, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.93, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", 1.03, d));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -9.03, d));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", .05, d));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));*/

        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

