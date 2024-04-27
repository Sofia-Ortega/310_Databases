package org.example;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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


    void getAll() {
        // Get all keys
        Set<String> keys = jedis.keys("*");

        // Iterate over keys and get their values
        for (String key : keys) {
            String value = jedis.get(key);
            System.out.println(key + ": " + value);
        }


    }
}
