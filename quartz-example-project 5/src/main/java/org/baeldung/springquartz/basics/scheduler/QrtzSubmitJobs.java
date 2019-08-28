package org.baeldung.springquartz.basics.scheduler;

import org.baeldung.springquartz.basics.dao.ReportName;
import org.baeldung.springquartz.basics.service.ReportService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class QrtzSubmitJobs {

    private static Logger logger = LoggerFactory.getLogger(QrtzSubmitJobs.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Autowired
    ReportService reportService;

    private Scheduler scheduler;

    private JobKey jobKey;

    @PostConstruct
    public void createSchedule() throws SchedulerException {
        scheduler = schedulerFactory.getScheduler();
        logger.info( "Entering the createSchedule" );
        List<ReportName> reportNames = reportService.listReportDefs();
        schedulingJob(reportNames);
        scheduler.start();
    }

    private void schedulingJob(List<ReportName> reportNames) throws SchedulerException {
        for (ReportName r : reportNames) {
            jobKey = new JobKey( r.getName() );
            logger.info( "Starting.....  {}",jobKey.getName());
            if(!scheduler.checkExists( jobKey )) {
                logger.info("Job Doesn't exist. Putting in Schedule.... ");
                JobDetail jobDetail = JobBuilder.newJob().withIdentity( jobKey ).build();
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(r.getName()).withSchedule( CronScheduleBuilder.cronSchedule( r.getCronExpression() ) ).build();
                scheduler.scheduleJob( jobDetail, trigger );
            } else {
                CronTrigger prevCronTrigger = (CronTrigger) scheduler.getTrigger( new TriggerKey( r.getName() ) );
                if(!prevCronTrigger.getCronExpression().equalsIgnoreCase(r.getCronExpression())) {
                    CronTrigger updatedCronTrigger = TriggerBuilder.newTrigger().withIdentity(r.getName()).withSchedule( CronScheduleBuilder.cronSchedule( r.getCronExpression() ) ).build();
                    scheduler.rescheduleJob( prevCronTrigger.getKey(), updatedCronTrigger);
                    logger.info("It has been updated");
                }
            }
        }
    }

    /*@Bean(name = "My10SecJob")
    public JobDetailFactoryBean My10SecJob() {
        return SpringQrtzScheduler.createJobDetail( SampleJob.class, "My10SecJob");
    }

    @Bean(name = "cronTriggermyJob10")
    public CronTriggerFactoryBean cronTriggermyJob10(@Qualifier("My10SecJob") JobDetail jobDetail) {
        return SpringQrtzScheduler.createTrigger(jobDetail,"00 22 10 * * ?", "cronTriggermyJob10");
    }

    @Bean(name = "My20SecJob")
    public JobDetailFactoryBean My20SecJob() {
        return SpringQrtzScheduler.createJobDetail( SampleJob.class, "My20SecJob");
    }

    @Bean(name = "cronTriggermyJob20")
    public CronTriggerFactoryBean cronTriggermyJob20(@Qualifier("My20SecJob") JobDetail jobDetail) {
        return SpringQrtzScheduler.createTrigger(jobDetail,"00 24 10 * * ?", "cronTriggermyJob20");
    }

    @Bean(name = "My30SecJob")
    public JobDetailFactoryBean My30SecJob() {
        return SpringQrtzScheduler.createJobDetail( SampleJob.class, "My30SecJob");
    }

    @Bean(name = "cronTriggermyJob30")
    public CronTriggerFactoryBean cronTriggermyJob30(@Qualifier("My30SecJob") JobDetail jobDetail) {
        return SpringQrtzScheduler.createTrigger(jobDetail,"00 26 10 * * ?", "cronTriggermyJob30");
    }*/

}