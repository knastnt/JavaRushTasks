package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\kostya\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
//        Object i = logParser.execute("get event for date = \"30.01.2014 12:56:22\"");
//        Object i = logParser.execute("get ip for event = \"LOGIN\"");
        Object i = logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        System.out.println(i);
    }
}