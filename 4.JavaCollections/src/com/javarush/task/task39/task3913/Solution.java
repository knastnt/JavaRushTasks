package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\kostya\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        Integer i = logParser.getNumberOfSuccessfulAttemptToSolveTask( 15, null, null);
        System.out.println(i);
    }
}