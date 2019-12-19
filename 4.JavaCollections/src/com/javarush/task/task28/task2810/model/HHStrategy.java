package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class HHStrategy implements Strategy {
//    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data.html";
    private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try {
            Document doc = getDocument("Москва", 0);
            System.out.println(doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        Document doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent(userAgent).referrer("http://hh.ua").get();
        Elements elements = doc.select("[data-qa=\"vacancy-serp__vacancy\"]");
        if(elements==null) return null;
        for (Element element : elements) {
            Vacancy vacancy = new Vacancy();
            vacancy.setSiteName(doc.title());

            Elements e = element.select("[data-qa=\"vacancy-serp__vacancy-address\"]");
            if (e!=null && e.size()>0) vacancy.setCity(e.first().text());

            e = element.select("[data-qa=\"vacancy-serp__vacancy-employer\"]");
            if (e!=null && e.size()>0) vacancy.setCompanyName(e.first().text());

            e = element.select("[data-qa=\"b-vacancy-list-salary\"]");
            if (e!=null && e.size()>0){
                vacancy.setSalary(e.first().text());
            }else{
                vacancy.setSalary("");
            }

            e = element.select("[data-qa=\"vacancy-serp__vacancy-title\"]");
            if (e!=null && e.size()>0){
                vacancy.setTitle(e.first().text());
                vacancy.setUrl(e.first().attr("href"));
            }

            System.out.println();
        }
        return null;
    }
}
