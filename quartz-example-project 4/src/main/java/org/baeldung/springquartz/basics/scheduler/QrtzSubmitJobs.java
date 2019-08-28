package org.baeldung.springquartz.basics.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.quartz.JobDetail;

@Configuration
public class QrtzSubmitJobs {

    @Bean(name = "My10SecJob")
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
    }
}