package com.zaga.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyTimesheet {
    // public String id;

    public String projectId;
    public String dailyTimesheetId;
    public String projectName;
    // public String duration;
    // public String date;
    public Double hours;
    public LocalDate date;
    public String supportTicket;
    public List<String> clientOwners;
    public List<String> redHatOwners;
    public String description;
    public String timesheetType;
}
