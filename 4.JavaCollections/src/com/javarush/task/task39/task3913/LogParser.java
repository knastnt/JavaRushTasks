package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return (int) getAllLogEntries(after, before)
                    .map(entry -> entry.ip)
                    .distinct()
                    .count();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getAllLogEntries(after, before)
                .map(entry -> entry.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.name.equals(user))
                .map(entry -> entry.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == event)
                .map(entry -> entry.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == status)
                .map(entry -> entry.ip)
                .collect(Collectors.toSet());
    }


    @Override
    public Set<String> getAllUsers() {
        return getAllLogEntries(null, null)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) getAllLogEntries(after, before)
                .map(entry -> entry.name)
                .distinct()
                .count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return (int) getAllLogEntries(after, before)
                .filter(entry -> entry.name.equals(user))
                .map(entry -> entry.event)
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.ip.equals(ip))
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.LOGIN)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DOWNLOAD_PLUGIN)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.WRITE_MESSAGE)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.SOLVE_TASK)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.SOLVE_TASK && entry.eventNum == task)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DONE_TASK)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DONE_TASK && entry.eventNum == task)
                .map(entry -> entry.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == event && entry.name.equals(user))
                .map(entry -> entry.time)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == Status.FAILED)
                .map(entry -> entry.time)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == Status.ERROR)
                .map(entry -> entry.time)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        try {
            return getAllLogEntries(after, before)
                    .filter(entry -> entry.name.equals(user) && entry.event == Event.LOGIN)
                    .map(entry -> entry.time)
                    .sorted((o1, o2) -> o1.compareTo(o2))
                    .findFirst()
                    .get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        try {
            return getAllLogEntries(after, before)
                    .filter(entry -> entry.name.equals(user) && entry.event == Event.SOLVE_TASK && entry.eventNum == task)
                    .sorted((o1, o2) -> o1.time.compareTo(o2.time))
                    .findFirst()
                    .get()
                    .time;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        try {
            return getAllLogEntries(after, before)
                    .filter(entry -> entry.name.equals(user) && entry.event == Event.DONE_TASK && entry.eventNum == task)
                    .sorted((o1, o2) -> o1.time.compareTo(o2.time))
                    .findFirst()
                    .get()
                    .time;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.name.equals(user) && entry.event == Event.WRITE_MESSAGE)
                .map(entry -> entry.time)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.name.equals(user) && entry.event == Event.DOWNLOAD_PLUGIN)
                .map(entry -> entry.time)
                .collect(Collectors.toSet());
    }

    private Entry parseLogString(String logString) throws ParseException {
        Pattern p = Pattern.compile("^(([0-9]{1,3}[\\.]){3}[0-9]{1,3})\\s+(.*)\\s+(([0-9]{1,2}[\\.]){2}[0-9]{4,5}\\s([0-9]{1,2}[:]){2}[0-9]{1,2})\\s+(.*)\\s+(OK|FAILED|ERROR)$");
        Matcher m = p.matcher(logString);

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




    private Stream<Entry> getAllLogEntries(Date after, Date before){
        try {
            return Files.walk(logDir)
                    .filter(path -> {
                        return path.toFile().isFile() && path.toString().toLowerCase().endsWith(".log");
                    })
                    .flatMap(path -> {
                        try {
                            return Files.lines(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                    })
                    .map(string -> {
                        try {
                            return parseLogString(string);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(entry -> entry != null)
                    .filter(entry -> entry.isInDate(after, before));
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
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

        public boolean isInDate(Date after, Date before){
            if (after != null) {
                if(!this.time.after(after)){
                    return false;
                }
            }
            if (before != null) {
                if(!this.time.before(before)){
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "ip='" + ip + '\'' +
                    ", name='" + name + '\'' +
                    ", time=" + time +
                    ", event=" + event +
                    ", eventNum=" + eventNum +
                    ", status=" + status +
                    '}';
        }
    }
}