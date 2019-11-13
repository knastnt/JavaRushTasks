package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int count = 0;
        for (Entry<K, List<V>> entry : map.entrySet()) {
            for (V value : entry.getValue()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public V put(K key, V value) {
        V toReturn = null;
        //напишите тут ваш код
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<V>());
        }
        if(map.get(key).size() > 0){
            toReturn = map.get(key).get(map.get(key).size()-1);
        }
        if(map.get(key).size() >= repeatCount) {
            map.get(key).remove(0);
        }
        map.get(key).add(value);

        return toReturn;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if(map.containsKey(key)){
            V toReturn = map.get(key).remove(0);
            if(map.get(key).size()==0) {
                map.remove(key);
            }
            return toReturn;
        }else{
            return null;
        }
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        Collection<V> collection = new ArrayList<>();
        for (Entry<K, List<V>> entry : map.entrySet()) {
            collection.addAll(entry.getValue());
        }
        return collection;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (Entry<K, List<V>> entry : map.entrySet()) {
            for (V val : entry.getValue()) {
                if(value.equals(val)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}