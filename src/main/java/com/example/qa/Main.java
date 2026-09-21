package com.example.qa;

public class Main {
    public static void main(String[] args) {
        int[] statusCodes = {200, 404, 201, 500, 204};
        for (int statusCode : statusCodes) {
            if (statusCode == 404) {
                continue;
            }
            if (statusCode == 500) {
                System.out.println("Server error, stopping");
                break;
            }
            System.out.println("Checking status: " + statusCode);
        }
        System.out.println("Check finished");
    }
}