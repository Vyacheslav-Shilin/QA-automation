package com.example.qa;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        List<String> currencies = new ArrayList<>();
        currencies.add("USD");
        currencies.add("EUR");
        currencies.add("USD");
        currencies.add("KZT");

        System.out.println("List count: " + currencies.size());

        Set<String> uniqueCurrencies = new LinkedHashSet<>();
        for (String currency : currencies) {
            uniqueCurrencies.add(currency);
        }

        System.out.println("Set count: " + uniqueCurrencies.size());

        Map<String, String>  currencyNames = new LinkedHashMap<>();
        currencyNames.put("USD", "US Dollar");
        currencyNames.put("EUR", "Euro");
        currencyNames.put("KZT", "Tenge");

        for (String key : uniqueCurrencies) {
            System.out.println(key + " = " + currencyNames.get(key));
        }


    }
}
