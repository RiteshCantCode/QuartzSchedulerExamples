package com.concretepage.job;

import java.util.Date;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class My10SecJob extends QuartzJobBean {

    private ImportReportService importReportService;
    private String report_names;
    private static final Logger log = LoggerFactory.getLogger(My10SecJob.class);

    public void setReport_name(String report_names) {
        this.report_names = report_names;
    }

    public void setImportReportService(ImportReportService importReportService) {
        this.importReportService = importReportService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("*********************************Entering My10SecJob*****************************");
        String[] report_name = report_names.split(",");
        for (int i = 0; i < report_name.length;i++ ) {
            try {
                importReportService.execute(report_name[i]);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("finished My10SecJob {}", report_name[i]);
        }
        log.info("*********************************Exiting my10SecJob*******************************");
    }

    //public
}