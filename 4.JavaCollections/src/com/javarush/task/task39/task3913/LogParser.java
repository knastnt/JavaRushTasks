package com.javarush.task.task39.task3913;

import com.javarush.task.task37.task3708.storage.Storage;
import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser implements IPQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        List<Entry> entries = parseLogStrings(readAllLinesFromFiles(getLogFiles(logDir)));

        System.out.println();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return 0;
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return null;
    }

    private Set<Path> getLogFiles(Path dir){
        Set<Path> toReturn = new LinkedHashSet<>();
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(attrs.isRegularFile() && file.toString().toLowerCase().endsWith(".log")){
                        toReturn.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
        return toReturn;
    }

    private List<String> readAllLinesFromFiles(Set<Path> files){
        List<String> toReturn = new LinkedList<>();
        for (Path file : files) {
            try {
                List<String> lines = Files.readAllLines(file);
                toReturn.addAll(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toReturn;
    }

    private Entry parseLogString(String logString) throws ParseException {
        Pattern p = Pattern.compile("^(([0-9]{1,3}[\\.]){3}[0-9]{1,3})\\s+(.*)\\s+(([0-9]{1,2}[\\.]){2}[0-9]{4}\\s([0-9]{1,2}[:]){2}[0-9]{1,2})\\s+(.*)\\s+(OK|FAILED|ERROR)$");
        Matcher m = p.matcher(logString);
        //return m.matches();
        if (m.find() && m.groupCount()==8){
//            for(int i = 0; i < m.groupCount()+1; i++) {
//                String d = m.group(i);
//                System.out.println(i + "  " + d);
//            }
            Entry entry = new Entry();
            entry.ip = m.group(1);
            entry.name = m.group(3);
            entry.time = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(m.group(4));
            entry.tryParseEvent(m.group(7));
            entry.status = m.group(8);
            return entry;
        }else{
            throw new ParseException("Строка не соответствует шиблону. " + logString, 0);
        }
    }

    private List<Entry> parseLogStrings(List<String> list){
        List<Entry> toReturn = new LinkedList<>();
        for (String s : list) {
            try {
                toReturn.add(parseLogString(s));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return toReturn;
    }


    private class Entry {
        public String ip;
        public String name;
        public Date time;
        public Event event;
        public Integer eventNum;
        public String status;

        public void tryParseEvent(String string) throws ParseException{
            String[] splitted = string.split("\\s+");

            boolean exists = true;
            try {
                event = Event.valueOf(splitted[0]);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }

            if (splitted.length > 1)
                eventNum = Integer.parseInt(splitted[1]);
        }
    }
}