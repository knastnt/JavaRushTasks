package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.nodes.Document;

import java.io.File;
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
//        StringBuffer vacanciesHtml = new StringBuffer(
//                "<!DOCTYPE html>\n" +
//                        "<html lang=\"ru\">\n" +
//                        "<head>\n" +
//                        "    <meta charset=\"utf-8\">\n" +
//                        "    <title>Вакансии</title>\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//                        "<table>\n" +
//                        "    <tr>\n" +
//                        "        <th>Title</th>\n" +
//                        "        <th>City</th>\n" +
//                        "        <th>Company Name</th>\n" +
//                        "        <th>Salary</th>\n" +
//                        "    </tr>"
//        );
//        for (Vacancy vacancy : vacancies) {
//            vacanciesHtml.append(String.format(
//                    "    <tr class=\"vacancy\">\n" +
//                            "        <td class=\"title\"><a href=\"%s\">%s</a></td>\n" +
//                            "        <td class=\"city\">%s</td>\n" +
//                            "        <td class=\"companyName\">%s</td>\n" +
//                            "        <td class=\"salary\">%s</td>\n" +
//                            "    </tr>",
//                    vacancy.getUrl(),
//                    vacancy.getTitle(),
//                    vacancy.getCity(),
//                    vacancy.getCompanyName(),
//                    vacancy.getSalary()
//            ));
//        }
//        vacanciesHtml.append(
//                "    <tr class=\"vacancy template\" style=\"display: none\">\n" +
//                        "        <td class=\"title\"><a href=\"url\"></a></td>\n" +
//                        "        <td class=\"city\"></td>\n" +
//                        "        <td class=\"companyName\"></td>\n" +
//                        "        <td class=\"salary\"></td>\n" +
//                        "    </tr>\n" +
//                        "</table>\n" +
//                        "</body>\n" +
//                        "</html>"
//        );
//        return vacanciesHtml.toString();
        return null;
    }
    private void updateFile(String html){
//        try {
//            Files.write(Paths.get(filePath), html.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
