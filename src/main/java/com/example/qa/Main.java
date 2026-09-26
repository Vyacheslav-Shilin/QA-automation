package com.example.qa;

public class Main {
    public static void main(String[] args) {
        User user = new User(201, "Anna", true);



        User secondUser = new User(202, "Max", false);

        user.setName("Maria");
        secondUser.setActive(true);

        System.out.println("User: " + user.getId() + ", name: " + user.getName() + ", active:" + user.isActive());
        System.out.println("User: " + secondUser.getId() + ", name: " + secondUser.getName() + ", active:" + secondUser.isActive());

    }
}

