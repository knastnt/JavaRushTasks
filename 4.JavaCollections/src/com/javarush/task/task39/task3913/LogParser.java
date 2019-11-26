package com.javarush.task.task39.task3913;

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

public class LogParser implements IPQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();

        List<Entry> entries = filterOutDateEntries(parseLogStrings(readAllLinesFromFiles(getLogFiles(logDir))), after, before);

        for (Entry entry : entries) {
            uniqueIPs.add(entry.ip);
        }

        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> IPs = new HashSet<>();

        List<Entry> entries = filterOutDateEntries(parseLogStrings(readAllLinesFromFiles(getLogFiles(logDir))), after, before);

        for (Entry entry : entries) {
            if (entry.name.equals(user)){
                IPs.add(entry.ip);
            }
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> IPs = new HashSet<>();

        List<Entry> entries = filterOutDateEntries(parseLogStrings(readAllLinesFromFiles(getLogFiles(logDir))), after, before);

        for (Entry entry : entries) {
            if (entry.event.equals(event)){
                IPs.add(entry.ip);
            }
        }

        return IPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> IPs = new HashSet<>();

        List<Entry> entries = filterOutDateEntries(parseLogStrings(readAllLinesFromFiles(getLogFiles(logDir))), after, before);

        for (Entry entry : entries) {
            if (entry.status.equals(status)){
                IPs.add(entry.ip);
            }
        }

        return IPs;
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
        Pattern p = Pattern.compile("^(([0-9]{1,3}[\\.]){3}[0-9]{1,3})\\s+(.*)\\s+(([0-9]{1,2}[\\.]){2}[0-9]{4,5}\\s([0-9]{1,2}[:]){2}[0-9]{1,2})\\s+(.*)\\s+(OK|FAILED|ERROR)$");
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
            entry.tryParseStatus(m.group(8));
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

    private List<Entry> filterOutDateEntries(List<Entry> list, Date after, Date before){
        List<Entry> toRetutn = new LinkedList<>();
        for (Entry entry : list) {
            if (after != null) {
                if(!entry.time.after(after)){
                    continue;
                }
            }
            if (before != null) {
                if(!entry.time.before(before)){
                    continue;
                }
            }
            toRetutn.add(entry);
        }
        return toRetutn;
    }


    private class Entry {
        public String ip;
        public String name;
        public Date time;
        public Event event;
        public Integer eventNum;
        public Status status;

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

        public void tryParseStatus(String string) throws ParseException{
            try {
                status = Status.valueOf(string);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        }
    }
}