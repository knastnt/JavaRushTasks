package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
//    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data.html";
    private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        for(int i=0; true; i++){
            try {
                Document doc = getDocument(searchString, i);

                Elements elements = doc.select("[data-qa=\"vacancy-serp__vacancy\"]");
                if (elements == null || elements.size() == 0) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
//                    URL url = new URL(doc.baseUri());
//                    vacancy.setSiteName(url.getProtocol() + "://" + url.getHost());
                    vacancy.setSiteName("http://hh.ua");

                    Elements e = element.select("[data-qa=\"vacancy-serp__vacancy-address\"]");
                    if (e != null && e.size() > 0) vacancy.setCity(e.first().text());

                    e = element.select("[data-qa=\"vacancy-serp__vacancy-employer\"]");
                    if (e != null && e.size() > 0) vacancy.setCompanyName(e.first().text());

                    e = element.select("[data-qa=\"vacancy-serp__vacancy-compensation\"]");
                    if (e != null && e.size() > 0) {
                        vacancy.setSalary(e.first().text());
                    } else {
                        vacancy.setSalary("");
                    }

                    e = element.select("[data-qa=\"vacancy-serp__vacancy-title\"]");
                    if (e != null && e.size() > 0) {
                        vacancy.setTitle(e.first().text());
                        vacancy.setUrl(e.first().attr("href"));
                    }

                    result.add(vacancy);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        Document doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).timeout(30000).userAgent(userAgent).referrer("http://hh.ua").get();
        return doc;
    }
}
