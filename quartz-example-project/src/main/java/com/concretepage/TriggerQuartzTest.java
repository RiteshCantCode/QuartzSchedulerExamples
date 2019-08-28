package com.concretepage;

import java.util.Calendar;
import java.util.Date;

import com.concretepage.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TriggerQuartzTest {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schfa = new StdSchedulerFactory();
        Scheduler sch = schfa.getScheduler();
        JobDetail jobdetailA = JobBuilder.newJob(MyJob.class).withIdentity(args[0], "mygroup1").build();
        JobDetail jobdetailB = JobBuilder.newJob(MyJob.class).withIdentity(args[1], "mygroup1").build();
        //Executes only one time
        //jobdetailA.getJobDataMap().put("count",0);
        /*Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger1", "mygroup1")
                .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 10000))//.withSchedule(CronScheduleBuilder.cronSchedule("22 11 * * thu"))
                .build();
        Trigger triggerB = TriggerBuilder.newTrigger().withIdentity("mytrigger2", "mygroup1")
                .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 10000))//.withSchedule(CronScheduleBuilder.cronSchedule("22 11 * * thu"))
                .build();*/
        sch.scheduleJob(jobdetailA, gettrigger());
        System.out.println("Starting...");
        sch.start();
        Thread.sleep(70L * 1000L);
        System.out.println("Finished");
        sch.scheduleJob(jobdetailB, gettrigger());
        System.out.println("Starting...");
        sch.start();
        Thread.sleep(70L * 1000L);
        System.out.println("Finished");
        sch.shutdown(true);
    }

    private static Trigger gettrigger() {
        return TriggerBuilder.newTrigger().withIdentity("mytrigger1", "mygroup1")
                .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 10000))//.withSchedule(CronScheduleBuilder.cronSchedule("22 11 * * thu"))
                .build();
    }


}