package org.baeldung.springquartz.basics.service;

import org.baeldung.springquartz.basics.dao.ReportName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportService {

    public List<ReportName> listReportDefs() {

        ReportName reportName = null;
        List<ReportName> reportNames = new ArrayList<ReportName>();
        reportName = new ReportName();
        reportName.setName( "Ritesh" );
        reportName.setCronExpression( "00 15 09 ? * Mon-Fri" );
        reportNames.add( reportName );


        reportName = new ReportName();
        reportName.setName( "Madhuri" );
        reportName.setCronExpression( "00 17 11 ? * Mon" );
        reportNames.add( reportName );
        return reportNames;
    }
}
