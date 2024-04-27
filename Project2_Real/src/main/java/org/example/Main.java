package org.example;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Connection;

import java.util.List;
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

        /*
        dataAccess.addProduct("apple", "3.22", "1", "sofia");
        dataAccess.addProduct("orange", "6.22", "5", "sofia");
        dataAccess.addProduct("banana", "2.22", "4", "sofia");
        dataAccess.addProduct("grape", "10.22", "40", "ezra");

         */

        List<Map<String, String>> products = dataAccess.getProducts();
        for(Map<String, String> product : products) {
            System.out.println(product.get("productName"));
            System.out.println(product.get("userName"));
            System.out.println(product.get("price"));
            System.out.println(product.get("quantity"));
            /*
            for (Map.Entry<String, String> entry : product.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + ": " + value);
            }
            */
            System.out.println(); // To separate each product's details        }
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