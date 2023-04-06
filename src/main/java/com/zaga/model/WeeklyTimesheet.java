package com.zaga.model;

import java.time.LocalDate;
import java.util.List;

import io.quarkus.qute.TemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TemplateData
public class WeeklyTimesheet {

    public String projectId;
    public String employeeName;
    public String weeklyTimesheetId;
    public String duration;
    public LocalDate startDate;
    public LocalDate endDate;
    public List<DailyTimesheet> timesheets;
    public TimesheetType timesheetType;
}
