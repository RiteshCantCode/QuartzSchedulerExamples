package org.baeldung.springquartz.basics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ImportReportService {
    private static final Logger log = LoggerFactory.getLogger(ImportReportService.class);

    public void execute(String report_name) throws InterruptedException {

        Date report_date = new Date();
        Boolean success = false;
        int tries = 4;
        log.info("import starting {}",report_name);
        while (tries > 0) {
            success = import_report(report_name, new Date(), 120);
            if (success) {
                log.info("Current Date: {} Report Name is {}",report_date,report_name);
                break;
            } else {
                tries--;
            }
        }
        if (!success) {
            log.info("Report has Failed");
        }
        log.info("Import Finished. {} {} {}",success,report_name,report_date);
    }

    private Boolean import_report(String report_name, Date report_date, int rety_timeout) throws InterruptedException {

        boolean success = false;
        log.info("inside in import_report");
        String result = load_report_to_database(report_name, report_date);
        if (result.equals("success")) {
            success = true;
        } else {
            Thread.sleep(rety_timeout);
            success = false;
        }

        return success;
    }

    private String load_report_to_database(String report_name, Date report_date) {
        String loadtodatabaseresult = "success";
        log.info("inside in load_report_to_database");

        return loadtodatabaseresult;
    }
}
