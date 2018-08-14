package com.thorn.springboost.controller;

import com.thorn.springboost.dao.postMapper;
import com.thorn.springboost.model.user;
import io.lettuce.core.RedisClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@MapperScan("com.thorn.springboost.dao")
public class test {

    @Autowired
    postMapper postMapper;

    private static org.jboss.logging.Logger
            logger= org.jboss.logging.Logger.getLogger(test.class);

    @PostMapping("/home")
    public String index(@RequestParam(value = "test")String test){
        logger.info(test);
        RedisClient.create("local");
        //System.out.println(postMapper.findPost("",1).get(0).getTitle());
        return "forward:/get";
    }
    @PostMapping("/get")
    public String get(@RequestBody user user){
        logger.info("fdsfsd");
        user user1=new user();
        user1.setUsername();
        user1.setPassword();
        logger.info(user.getUsername()+":"+user.getPassword());
        //System.out.println(postMapper.findPost("",1).get(0).getTitle());
        return "/index";
    }
}
