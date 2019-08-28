package org.baeldung.springquartz.basics.scheduler;

import org.baeldung.springquartz.basics.service.ImportReportService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImportReportService importReportService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        try {
            importReportService.execute(context.getJobDetail().getKey().getName() );
        } catch (InterruptedException e) {
            logger.error( e.getMessage(),e );
        }

        logger.info("Next job scheduled @ {}", context.getNextFireTime());
    }
}
