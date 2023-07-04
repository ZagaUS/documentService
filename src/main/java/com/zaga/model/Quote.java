package com.zaga.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    public String quoteId;
    public String projectId;
    public String quoteStatus;
    public LocalDate date; 
    public LocalDate startDate;
    public LocalDate endDate;
    public LocalDate validDate;
    // public String from; // companyAddress
    public String to; // clientAddress
    // public String serviceDescription;
    public Float totalManDays; // changed fieldname
    public Float unitPrice; // changed fieldname
    public Currency clientCurrency;
    public Float totalAmount; // changed datatype

    // public String duration; // peroid
    // public String manDays;
    public Float totalPrice;
    public Float whtAmount;
    public String employeeRole;
    public String employeeName;
    public String projectName;
    // public String gstPercent;

}
