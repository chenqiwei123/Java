package com.example.demo.Volatile;

public class Test {
    private static Test instance = null;

    public static Test getSingInstance() {
        if (instance == null) {
            synchronized (Test.class) {
                instance = new Test();
            }
        }
        return  instance;
    }
    public void action() {
        System.out.println(">>>>>>>>>>");
    }

    public static void main(String[] args) {

    }
}
