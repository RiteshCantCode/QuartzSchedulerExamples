package com.concretepage.job;

import java.util.Date;
//import org.quartz.Job;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        String report_name = key.getName();
        //JobDataMap jobDataMap = jobDetail.getJobDataMap();
        //int count = jobDataMap.getInt("count");
        //count++;
        //System.out.println("Printing count : "+count);
        //jobDataMap.put("count",count);
        Date report_date = new Date();
        Boolean success = false;
        int tries = 4;
        System.out.println("import starting "+report_name);
        while(tries > 0) {
            try {
                success = import_report(report_name, new Date(),120);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            if(success) {
                System.out.println("For Job key: " + key + ", Current Date: " + report_date+ " Number is "+ tries + " Report Name is " + report_name);
                break;
            }
            else {

            }
        }
        if(!success) {
            System.out.println("Report has Failed");
        }
        System.out.println("Import Finished. "+ success + " " + report_name + " " + report_date);
    }

    private Boolean import_report(String report_name, Date report_date, int rety_timeout) throws InterruptedException {

        boolean success = false;
        System.out.println("inside in import_report");
        String result = load_report_to_database(report_name,report_date);
        if(result.equals("success")) {
            success = true;
        }
        else {
            Thread.sleep(rety_timeout);
            success = false;
        }

        return success;
    }

    private String load_report_to_database(String report_name, Date report_date) {
        String loadtodatabaseresult = "success";
        System.out.println("inside in load_report_to_database");

        return loadtodatabaseresult;
    }

    //public
}