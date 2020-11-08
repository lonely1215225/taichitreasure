package edu.hunau.zzy;

public class Test {
    public static void main(String[] args) {
        String s="sas\\sadas";
        String replace = s.replace("\\\\", "/");
        System.out.println(replace);
    }
}
