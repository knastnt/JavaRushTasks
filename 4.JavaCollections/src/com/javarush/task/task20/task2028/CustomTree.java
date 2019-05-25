package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    List<Entry<String>> list = new ArrayList<>();

    public CustomTree() {
        root = new Entry<>("root");
        list.add(root);
    }

    @Override
    public String get(int index){
        throw new UnsupportedOperationException();
    }
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }
    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }
    public String remove(int index){
        throw new UnsupportedOperationException();
    }
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }



    @Override
    public int size() {
        return list.size()-1;
    }

    @Override
    public boolean add(String elementName) {
        Entry<String> newEntry = new Entry<>(elementName);
        for (Entry<String> entry : list) {
            if (entry.availableToAddLeftChildren) {
                entry.leftChild = newEntry;
                entry.availableToAddLeftChildren = false;
                newEntry.parent = entry;
                break;
            }
            if (entry.availableToAddRightChildren) {
                entry.rightChild = newEntry;
                entry.availableToAddRightChildren = false;
                newEntry.parent = entry;
                break;
            }
        }
        return list.add(newEntry);
    }

    public String getParent(String s) {
        for (Entry<String> entry : list) {
            if (entry.elementName.equals(s)) {
                return entry.parent.elementName;
            }
        }
        return null;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        /*private boolean add (Entry<T> entry){
            if (isAvailableToAddChildren()) {
                //запихнем сюда
                if (availableToAddLeftChildren) {
                    //пихаем влево
                    leftChild = entry;
                    availableToAddLeftChildren = false;
                }else{
                    //пихаем вправо
                    rightChild = entry;
                    availableToAddRightChildren = false;
                }
                return true;
            }else{
                //передаем дочерним
                
            }
        }*/
    }
}
