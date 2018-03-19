package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            if(this.getClass() == B.class) throw new CloneNotSupportedException();
            return super.clone();
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A(1,2);
        A aa = (A)a.clone();
        B b = new B(3, 4, "5");
        //B bb = (B)b.clone();
        C c = new C(6, 7, "8");
        C cc = (C)c.clone();
        System.out.println(a);
        System.out.println(aa);
        System.out.println("");
        System.out.println(b);
        //System.out.println(bb);
        System.out.println("");
        System.out.println(c);
        System.out.println(cc);
    }
}
