package com.thorn.springboost.controller;

import com.thorn.springboost.dao.postMapper;
import com.thorn.springboost.model.user;
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

    @GetMapping("/home")
    public String index() {
        logger.info(postMapper.findpost("", 1).get(0).getTitle());
        return "/index";
    }
    @PostMapping("/get")
    public String get(@RequestBody user user){
        logger.info("fdsfsd");
        logger.info(user.getUsername()+":"+user.getPassword());
        //System.out.println(postMapper.findPost("",1).get(0).getTitle());
        return "/index";
    }
}
