package com.zaga.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyTimesheet {

    public String projectId;
    public String dailyTimesheetId;
    public String projectName;
    public String duration;
    public String date;
    public String supportTicket;
    public List<String> clientOwners;
    public List<String> redHatOwners;
    public String description;
    public String timesheetType;
}
