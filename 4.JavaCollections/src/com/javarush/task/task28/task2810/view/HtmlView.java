package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HtmlView implements View {
    private final String filePath =  "./4.JavaCollections/src/" + this.getClass().getPackage().toString().substring(8).replace('.', '/') + "/vacancies.html";

    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
        try {
            getDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        try {
            Document document = getDocument();
            Element template = document.getElementsByClass("template").first();
            Element copy = template.clone();
            copy.removeClass("template").removeAttr("style");
            document.select(".vacancy:not(.template)").remove();

            for (Vacancy vacancy : vacancies) {
                Element element = copy.clone();
                element.getElementsByClass("city").first().text(vacancy.getCity());
                element.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                element.getElementsByClass("salary").first().text(vacancy.getSalary());
                element.getElementsByTag("a").first().attr("href", vacancy.getUrl()).text(vacancy.getTitle());
                document.select(".vacancy.template").before(element.outerHtml());
            }
            return document.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }
    private void updateFile(String html){
        try(FileOutputStream fos = new FileOutputStream(filePath)){
                fos.write(html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
