package com.example.qa;

public class Main {
    public static void main(String[] args) {

        int statusCode = 499;
        if (statusCode >= 200 && statusCode <= 299) {
            System.out.println("Success");
        } else if (statusCode >= 400 && statusCode <= 499) {
            System.out.println("Client error");
        } else if (statusCode >= 500 && statusCode <= 599) {
            System.out.println("Server error");
        } else {
            System.out.println("Other status");
        }
    }
}
