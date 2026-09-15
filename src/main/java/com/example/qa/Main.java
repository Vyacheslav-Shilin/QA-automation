package com.example.qa;

public class Main {
    public static void main(String[] args) {
        int statusCode = 200;
        String currency = "USD";
        long userId = 9000000000L;
        double responseTimeSeconds = 0.75;
        boolean successful = true;

        System.out.println("statusCode: " + statusCode);
        System.out.println("currency: " + currency);
        System.out.println("userId: " + userId);
        System.out.println("responseTimeSeconds: " + responseTimeSeconds);
        System.out.println("successful: " + successful);

        currency = "EUR";
        System.out.println("currency: " + currency);

        statusCode = 404;
        System.out.println("statusCode: " + statusCode);

        successful = false;
        System.out.println("successful: " + successful);

        int responseTimeMs = 750;
        double responseTimeAsDouble = responseTimeMs;
        System.out.println("responseTimeAsDouble: " + responseTimeAsDouble);


        int wholeSeconds = (int) responseTimeSeconds;
        System.out.println("wholeSeconds: " + wholeSeconds);

        int retryCount = 2;
        double averageResponseTime = 1.85;
        int averageWholeSeconds = (int) averageResponseTime;
        System.out.println("Retries: " + retryCount + ", average: " + averageResponseTime +  ", whole second: " + averageWholeSeconds);

        retryCount = 3;
        System.out.println("Retries: " + retryCount + ", average: " + averageResponseTime +  ", whole second: " + averageWholeSeconds);
    }
}
