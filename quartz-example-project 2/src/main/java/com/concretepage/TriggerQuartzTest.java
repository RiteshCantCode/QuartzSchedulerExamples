package com.concretepage;

import org.quartz.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TriggerQuartzTest {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }

}