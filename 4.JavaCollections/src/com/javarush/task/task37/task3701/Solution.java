package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    public class RoundIterator implements Iterator {
        private Iterator iterator = Solution.super.iterator();

        @Override
        public boolean hasNext() {
            refreshIterator();
            return iterator.hasNext();
        }

        @Override
        public Object next() {
            refreshIterator();
            return iterator.next();
        }

        @Override
        public void remove() {
            iterator.remove();
        }

        @Override
        public void forEachRemaining(Consumer action) {
            iterator.forEachRemaining(action);
        }

        private void refreshIterator(){
            if(Solution.this.size()>0 && iterator.hasNext() == false){
                iterator = Solution.super.iterator();
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }
}
