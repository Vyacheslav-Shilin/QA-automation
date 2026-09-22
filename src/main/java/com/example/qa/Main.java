package com.example.qa;

public class Main {
    public static void main(String[] args) {
        int[] statusCodes = {200, 404, 201, 500, 299, 300};
        for (int statusCode : statusCodes) {
            boolean successful = isSuccessfulStatus(statusCode);
            System.out.println("Status: " + statusCode + ", successful: " + successful);
        }
    }

    public static boolean isSuccessfulStatus(int statusCode) {
        return statusCode >= 200 && statusCode <= 299;
    }
}
