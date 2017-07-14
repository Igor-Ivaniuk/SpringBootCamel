package com.example.camel;

/**
 * Created by IgorIvaniuk on 13.07.2017.
 */

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class)
public class SampleCamelApplicationTest {

    @Autowired
    private CamelContext camelContext;

    @Before
    public void init() throws Exception {
        // As we disabled autostart of SayHiRoute, we must start it manually for the test below to pass
        camelContext.startRoute("supermegaroute");
    }

    @Test
    public void shouldProduceMessages() throws Exception {
        // we expect that one or more messages is automatic done by the Camel
        // route as it uses a timer to trigger
        NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(1).create();
        assertTrue(notify.matches(10, TimeUnit.SECONDS));
    }

}