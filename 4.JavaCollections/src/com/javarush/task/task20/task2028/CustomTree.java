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
        //throw new UnsupportedOperationException();
        String me =  list.get(index).elementName;
        String left = list.get(index).leftChild != null ? list.get(index).leftChild.elementName : "null";
        String right = list.get(index).rightChild != null ? list.get(index).rightChild.elementName : "null";
        return me + " - " + left + ", " + right;
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

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        for (Entry<String> entry : list) {
            if (entry.elementName.equals((String)o)){
                entry.remove();
                break;
            }
        }
        for (int i = list.size() - 1; i >= 1; i--) {
            if (list.get(i).parent == null){
                list.remove(i);
            }
        }
        return true;
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

        public void remove() {
            if (!availableToAddLeftChildren) {
                leftChild.remove();
                leftChild = null;
                availableToAddLeftChildren = true;
            }
            if (!availableToAddRightChildren) {
                rightChild.remove();
                rightChild = null;
                availableToAddRightChildren = true;
            }
            if (parent.leftChild == this) {
                parent.leftChild = null;
                parent.availableToAddLeftChildren = true;
            }
            if (parent.rightChild == this) {
                parent.rightChild = null;
                parent.availableToAddRightChildren = true;
            }
            parent = null;
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
