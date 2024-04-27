package org.example;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class DataAccess {

    Jedis jedis;

    DataAccess() {
        this.jedis = new Jedis("redis://default:sBXnu6lcpkO1Kq34tNmvGKQRVw2QK1ON@redis-10821.c11.us-east-1-2.ec2.redns.redis-cloud.com:10821");

    }

    void test() {

    }


    void addUser(String userName, String creditCard) {
        jedis.hset("users", userName, creditCard);
    }

    void deleteUser(String userName) {
        jedis.hdel("users", userName);
    }

    Map<String, String> getUsers() {
        return jedis.hgetAll("users");
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
