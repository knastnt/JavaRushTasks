package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\kostya\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
    }
}