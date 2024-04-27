package org.example;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Connection;

import java.util.Map;
import java.util.Set;


public class Main {


    public static void main(String[] args) {
        System.out.println("Hello world!");

        DataAccess dataAccess = new DataAccess();

        /*
        dataAccess.addUser("sofia", "123");
        dataAccess.addUser("ezra", "123");
        dataAccess.addUser("andrew", "123");

         */

        Set<String> mySet = dataAccess.getAllUserIds();
        System.out.println("set:");
        for(String s : mySet)
            System.out.println(s);

        System.out.println("-=------------l");
        Map<String, String> users = dataAccess.getUsers();
        for (Map.Entry<String, String> entry : users.entrySet()) {
            String userId = entry.getKey();
            String creditCard = entry.getValue();
            System.out.println("User ID: " + userId + ", Credit Card: " + creditCard);
        }





        /*
        String cachedResponse = jedis.get("events/city/rome");
        System.out.println("1:" + cachedResponse);

        jedis.set("events/city/rome", "32,15,223,828");
        cachedResponse = jedis.get("events/city/rome");


        System.out.println("2: " + cachedResponse);

         */




    }
}