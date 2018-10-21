package com.vinfast.ecosystem.restfulredis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisRestController {

    @Autowired
    private RedisDAO redisDAO;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public RedisEntity get(@RequestParam(value="key") String key) {
        return redisDAO.get(key);
    }

    @RequestMapping(value = "/set", method = RequestMethod.PUT)
    public void set(@RequestBody RedisEntity entity) {
        redisDAO.set(entity);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RedisEntity> list() {
        return redisDAO.list();
    }
}