package com.example.camel.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IgorIvaniuk on 13.07.2017.
 */
@Component("myBean")
public class SayHiBean {

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return say;
    }
}
