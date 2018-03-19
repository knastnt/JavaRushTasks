package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }



/*public boolean equals(Object n) {
        if (n instanceof Solution){

            if(first != null && last != null) {
                return ((Solution) n).first.equals(first) && ((Solution) n).last.equals(last);
            }else{
                if(first == null && last != null){
                    if(((Solution) n).first != null) return false;
                    return ((Solution) n).last.equals(last);
                }
                if(last == null && first != null){
                    if(((Solution) n).last != null) return false;
                    return ((Solution) n).first.equals(first);
                }
                return ((Solution) n).first == null && ((Solution) n).last == null;
            }
        }
        return false;
    }*/


    /*public boolean equals(Solution n){
        if (n instanceof Solution) {
            return this == n;
        }
        return  false;
    }*/


    /*public int hashCode() {
        //System.out.println(31 * first.hashCode() + last.hashCode());
        int firstH = 0;
        if(first != null) {
            first.hashCode();
        }
        int lastH = 0;
        if(last != null) {
            last.hashCode();
        }
        return firstH+lastH;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof Solution){
            Solution solution = (Solution) o;
            return Objects.equals(first, solution.first) &&
                    Objects.equals(last, solution.last);
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, last);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution(null, null));
        System.out.println(s.contains(new Solution(null, null)));
    }
}
