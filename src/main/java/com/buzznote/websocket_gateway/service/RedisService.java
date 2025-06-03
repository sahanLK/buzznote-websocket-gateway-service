package com.buzznote.websocket_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean isValidAccessToken(String userId, String token) {
        return getAccessToken(userId).equals(token);
    }

    private String getAccessToken(String email) {
        return (String) redisTemplate.opsForHash().get("USER:" + email, "accessToken");
    }

}
