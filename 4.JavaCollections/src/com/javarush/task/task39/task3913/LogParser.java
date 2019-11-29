package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public Set<Object> execute(String query) {
        QL ql;
        try {
            ql = parseQLString(query);
        } catch (ParseException e) {
            e.printStackTrace();
            return new HashSet<>();
        }


        Predicate<Entry> filter = entry -> ql.field2 == null || (entry.isEquals(ql.field2, ql.value));

        Function<Entry, Object> mapper = entry -> {
            try {
                return entry.get(ql.field1).getValue();
            } catch (NoSuchFieldException|IllegalAccessException e) {
                e.printStackTrace();
                return new HashSet<>();
            }
        };

        return getAllLogEntries(null, null).filter(filter).map(mapper).collect(Collectors.toSet());
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
                .filter(entry -> entry.user.equals(user))
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
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) getAllLogEntries(after, before)
                .map(entry -> entry.user)
                .distinct()
                .count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return (int) getAllLogEntries(after, before)
                .filter(entry -> entry.user.equals(user))
                .map(entry -> entry.event)
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.ip.equals(ip))
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.LOGIN)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DOWNLOAD_PLUGIN)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.WRITE_MESSAGE)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.SOLVE_TASK)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.SOLVE_TASK && entry.eventNum == task)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DONE_TASK)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DONE_TASK && entry.eventNum == task)
                .map(entry -> entry.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == event && entry.user.equals(user))
                .map(entry -> entry.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == Status.FAILED)
                .map(entry -> entry.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == Status.ERROR)
                .map(entry -> entry.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        try {
            return getAllLogEntries(after, before)
                    .filter(entry -> entry.user.equals(user) && entry.event == Event.LOGIN)
                    .map(entry -> entry.date)
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
                    .filter(entry -> entry.user.equals(user) && entry.event == Event.SOLVE_TASK && entry.eventNum == task)
                    .sorted((o1, o2) -> o1.date.compareTo(o2.date))
                    .findFirst()
                    .get()
                    .date;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        try {
            return getAllLogEntries(after, before)
                    .filter(entry -> entry.user.equals(user) && entry.event == Event.DONE_TASK && entry.eventNum == task)
                    .sorted((o1, o2) -> o1.date.compareTo(o2.date))
                    .findFirst()
                    .get()
                    .date;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.user.equals(user) && entry.event == Event.WRITE_MESSAGE)
                .map(entry -> entry.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.user.equals(user) && entry.event == Event.DOWNLOAD_PLUGIN)
                .map(entry -> entry.date)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return (int) getAllLogEntries(after, before)
                .map(entry -> entry.event)
                .distinct()
                .count();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getAllLogEntries(after, before)
                .map(entry -> entry.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.ip.equals(ip))
                .map(entry -> entry.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.user.equals(user))
                .map(entry -> entry.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == Status.FAILED)
                .map(entry -> entry.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.status == Status.ERROR)
                .map(entry -> entry.event)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        try {
            return (int) getAllLogEntries(after, before)
                    .filter(entry -> entry.event == Event.SOLVE_TASK && entry.eventNum.equals(task))
                    .count();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        try {
            return (int) getAllLogEntries(after, before)
                    .filter(entry -> entry.event == Event.DONE_TASK && entry.eventNum.equals(task))
                    .count();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.SOLVE_TASK)
                .collect(Collectors.toMap(entry -> {
                    return entry.eventNum; //key
                }, entry -> {
                    return 1; //value
                }, (integer1, integer2) -> {
                    return integer1+integer2; //присваиваем новое значение ключу. integer1 - пердыдущее значения этого ключа в мапе. integer2 - то, что вернулось в value строкой выше
                }));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getAllLogEntries(after, before)
                .filter(entry -> entry.event == Event.DONE_TASK)
                .collect(Collectors.toMap(entry -> {
                    return entry.eventNum; //key
                }, entry -> {
                    return 1; //value
                }, (integer1, integer2) -> {
                    return integer1+integer2; //присваиваем новое значение ключу. integer1 - пердыдущее значения этого ключа в мапе. integer2 - то, что вернулось в value строкой выше
                }));
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
            entry.user = m.group(3);
            entry.date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(m.group(4));
            entry.tryParseEvent(m.group(7));
            entry.tryParseStatus(m.group(8));
            return entry;
        }else{
            throw new ParseException("Строка не соответствует шиблону. " + logString, 0);
        }
    }

    private QL parseQLString(String QLString) throws ParseException {
        Pattern p = Pattern.compile("^get\\s+(\\w+)(\\s+for\\s+(\\w+)\\s*=\\s*\"(.*)\")?$");
        Matcher m = p.matcher(QLString);

        if (m.find() && m.groupCount()==4){
//            for(int i = 0; i < m.groupCount()+1; i++) {
//                String d = m.group(i);
//                System.out.println(i + "  " + d);
//            }
            QL ql = new QL();
            ql.field1 = m.group(1);
            ql.field2 = m.group(3);
            ql.value = m.group(4);
            return ql;
        }else{
            throw new ParseException("Запрос не соответствует шиблону. " + QLString, 0);
        }
    }

    private class QL {
        public String field1;
        public String field2;
        public String value;
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
        public String user;
        public Date date;
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
                if(!this.date.after(after)){
                    return false;
                }
            }
            if (before != null) {
                if(!this.date.before(before)){
                    return false;
                }
            }
            return true;
        }

        public boolean isFieldExist(String field) {
            try {
                Pair<Class, Object> fieldPair = this.get(field);
                return true;
            } catch (IllegalAccessException|NoSuchFieldException e) {
                return false;
            }
        }

        public Pair<Class, Object> get(String field) throws NoSuchFieldException, IllegalAccessException {
            Class key = this.getClass().getDeclaredField(field).getType();
            Object value = this.getClass().getDeclaredField(field).get(this);
            return new Pair<>(key, value);
        }

        public boolean isEquals(String field, String value) {
            try {
                Pair<Class, Object> fieldPair = this.get(field);

                if (fieldPair.getKey() == String.class){
                    return fieldPair.getValue().equals(value);
                }

                if (fieldPair.getKey().isEnum()){
                    //Поле по имени field имеет тип Enum, поэтому и сравнивать будем соответствующим образом
                    try {
                        Object required = Enum.valueOf(fieldPair.getKey(), value);
                        return fieldPair.getValue().equals(required);
                    }catch (IllegalArgumentException|NullPointerException e){
                        e.printStackTrace();
                        return false;
                    }
                }

                if (fieldPair.getKey() == Date.class){
                    try {
                        Date required = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(value);
                        return fieldPair.getValue().equals(required);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }

                return false;
            } catch (IllegalAccessException|NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "ip='" + ip + '\'' +
                    ", name='" + user + '\'' +
                    ", time=" + date +
                    ", event=" + event +
                    ", eventNum=" + eventNum +
                    ", status=" + status +
                    '}';
        }
    }
}