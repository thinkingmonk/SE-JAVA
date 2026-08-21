package org.fullstack;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int calc(int a, int b, String c) {
        switch (c){
            case "+":
                return a+b;
            default:
                System.out.println("Invalid Input");
                return -1;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("First number: ");
        int a = sc.nextInt();

        System.out.print("Second number: ");
        int b = sc.nextInt();

        System.out.print("Operator: ");
        String c = sc.next();

        int res = calc(a,b,c);
        System.out.println("Response: " + res);

        sc.close();

//            int s = printSum(1,6,"+");
//        System.out.println("Output: "+s);
//        int[] marks = {95, 98, 100};
//        try{
//            System.out.println(marks[5]);
//        } catch (Exception err){
//            System.out.println(err);
//        }
//        System.out.println("print");

//        for (int i = 0; i <= 5; i++) {
//            if (i == 3) {
//                break;   // skip printing 3
//            }
//            System.out.println(i);   // 0, 1, 2, 4, 5
//        }

//        for (int i=0; i<=9;i++){
//            System.out.println(i);
//        }

//        int start =1 ;
//        while(start<=9){
//            System.out.println(start);
//            start++;
//        }

//        int i = 1;
//        do {
//            System.out.println(i);
//            i++;
//        } while (i <= 5);


        int day = 1;

        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            default:
                System.out.println("Other day");
        }

        boolean isAdult = true;
        int age = 19;

        if (!isAdult) {
            System.out.println("adult");
        } else if(isAdult && age >= 18) {
            System.out.println("not adult");
        } else {
            System.out.println("Bye");
        }

        System.out.println(Math.max(5, 6));
        System.out.println(Math.min(5, 6));
        System.out.println(Math.sqrt(64));
        System.out.println(Math.pow(2, 3));
        System.out.println(Math.random());


//        int a = 1, b =2;
//        a *= 2;
//        ++a;
//        System.out.println(a);


//        final float PI = 3.14F;
//        PI = 1.1F;

        // explicit
        double amount = 249.99;
        int rupees =  (int) amount;
        System.out.println("rupees: "+rupees);

//        int[] marks = new int[3];
//        marks[0] = 99;   // Physics
//        marks[1] = 98;   // Chemistry
//        marks[2] = 100;  // English
//        System.out.println(marks[0]);
//        Arrays.sort(marks);
//        System.out.println(marks[0]);

        int[][] finalMarks = {
                {97, 78, 95},   // student 1
                {85, 88, 90}    // student 2
        };

        System.out.println(finalMarks[0][1]);





//        String name = "TESTE";


//        System.out.println(name.length());
//        System.out.println(name.charAt(2));
//        System.out.println(name.substring(1,1));
//        System.out.println(name.replace('T','Y'));
//        System.out.println(name.replaceFirst("TE","Y"));

//        String a = "Java";
//        String b = a;
////        String b = new String("Java");
//        System.out.println(a == b);
//        System.out.println(a.equals(b));
        // single line
        /*
        * @description
        */
    }
}