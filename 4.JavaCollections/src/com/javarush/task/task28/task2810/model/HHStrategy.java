package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try {
//            Document doc = Jsoup.connect(String.format(URL_FORMAT, "Москва", 2)).userAgent(userAgent).timeout(timeout).get();
            Document doc = Jsoup.connect(String.format(URL_FORMAT, "Москва", 2)).userAgent(userAgent).referrer("http://hh.ua").get();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
