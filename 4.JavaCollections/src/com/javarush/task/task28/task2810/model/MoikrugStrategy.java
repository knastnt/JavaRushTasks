package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
//    private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data2.html";
    private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        for(int i=0; true; i++){
            try {
                Document doc = getDocument(searchString, i);

                Elements elements = doc.select("#jobs_list .job");
                if (elements == null || elements.size() == 0) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
//                    URL url = new URL(doc.baseUri());
//                    vacancy.setSiteName(url.getProtocol() + "://" + url.getHost());
                    vacancy.setSiteName("https://moikrug.ru");

                    Elements e = element.select("span.location a");
                    if (e != null && e.size() > 0) {
                        vacancy.setCity(e.first().text());
                    } else {
                        vacancy.setCity("");
                    }

//                    e = element.select("span.company_name a");
//                    if (e != null && e.size() > 0) {
//                        vacancy.setCompanyName(e.first().text());
//                    } else {
//                        vacancy.setCompanyName("");
//                    }
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());

                    e = element.select(".salary .count");
                    if (e != null && e.size() > 0) {
                        vacancy.setSalary(e.first().text());
                    } else {
                        vacancy.setSalary("");
                    }

                    e = element.select(".title a");
                    if (e != null && e.size() > 0) {
                        vacancy.setTitle(e.first().text());
                        vacancy.setUrl("https://moikrug.ru" + e.first().attr("href"));
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
        Document doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).timeout(30000).userAgent(userAgent).referrer("https://moikrug.ru").get();
        return doc;
    }
}
