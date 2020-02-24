package com.WebApp3.WebApp3.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;
    @GetMapping(path="/hw")
    public String helloWorld()
    {
        return "Hello :)";
    }

    @GetMapping(path="/hw-bean/{name}")
    public HWBean helloWorldBean(@PathVariable String name)
    {
        return new HWBean(name);
    }

    @GetMapping(path="/gm")
    public String GMBean()
    {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

}
