package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.ReportTextProcessor;
import ru.andrey.caraccidentreport.dto.ReportTextDTO;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.ReportText;

public class ReportTextServices {

    public void addFullReportToDB(ReportTextDTO rtDTO) throws DataAccessException {
        String report = rtDTO.getText();

        ReportText rt = new ReportText();
        rt.setReportText(report);

        ReportTextProcessor rtp = new ReportTextProcessor();
        rtp.addReportText(rt);
    }

}
