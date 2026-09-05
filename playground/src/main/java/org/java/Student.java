package org.java;

public class Student {
    String name;
    static int rollNo;

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    void display() {
        System.out.println(rollNo + ": " + name);
    }
}
