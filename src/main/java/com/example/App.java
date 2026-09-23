package com.example;

public class App {
    public int add() {
        int a = 2;
        int b = 3;
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(new App().add());
    }
}
