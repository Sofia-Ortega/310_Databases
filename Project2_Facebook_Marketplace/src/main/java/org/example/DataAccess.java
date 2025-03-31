package org.example;

import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

public class DataAccess {

    Jedis jedis;

    DataAccess() {
        this.jedis = new Jedis( System.getenv("DATABASE_URL"));

    }

    void addUser(String userId, String creditCard) {
        jedis.hset("users:" + userId, "creditCard", creditCard);
    }

    void updateCreditCard(String userId, String creditCard) {
        jedis.hset("users:" + userId, "creditCard", creditCard);
    }

    void deleteUser(String userId) {
        jedis.del("users:" + userId);
    }

    String getCreditCard(String userId) {
        Map<String, String> m = jedis.hgetAll("users:" + userId);
        return m.get("creditCard");

    }

    Set<String> getAllUserIds() {
        return jedis.keys("users:*").stream()
                .map(key -> key.substring("users:".length()))
                .collect(Collectors.toSet());
    }


    Map<String, String> getUsers() {
        Map<String, String> allUsers = new HashMap<>();
        Set<String> userKeys = jedis.keys("users:*");
        for (String key : userKeys) {
            System.out.println("key:"+ key);

            Map<String, String> userData = jedis.hgetAll(key);
            allUsers.putAll(userData);
        }
        return allUsers;
    }


    void getAllKeys() {
        // Get all keys
        Set<String> keys = jedis.keys("*");

        // Iterate over keys and get their values
        System.out.println("--------- Keys ---------");
        for (String key : keys) {
            System.out.println(key);
        }
    }


    // -------------- PRODUCTS ------------------
    void addProduct(String productName, String price, String quantity, String userName) {
        jedis.hset("products:" + productName, "price", price);
        jedis.hset("products:" + productName, "quantity", quantity);
        jedis.hset("products:" + productName, "userName", userName);
    }

    void deleteProduct(String productName) {
        jedis.del("products:" + productName);
    }

    List<Map<String, String>> getProducts() {
        List<Map<String, String>> products = new ArrayList<Map<String, String>>();
        Set<String> productKeys = jedis.keys("products:*");
        for (String key : productKeys) {

            Map<String, String> productData = jedis.hgetAll(key);
            productData.put("productName", key.substring(9));
            products.add(productData);
        }
        return products;

    }

    Integer buyProduct(String productName) {
        String quantityStr = jedis.hget("products:" + productName, "quantity");
        Integer newQuantity = Integer.parseInt(quantityStr) - 1;

        jedis.hset("products:" + productName, "quantity", newQuantity+"");

        return newQuantity;

    }
}
