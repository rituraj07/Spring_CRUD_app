package com.WebApp3.WebApp3.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class userDaoServiceCmdRunner implements CommandLineRunner {

    private static final Logger log =
            LoggerFactory.getLogger(userDaoServiceCmdRunner.class);

    @Autowired
    private UserDaoService service;
    @Override
    public void run(String... args) throws Exception {
        log.info("/////////////////////////////////////////");
        User user=new User("aman", new Date());
        long insert =service.insert(user);
        log.info("new user :"+ user);
    }
}
