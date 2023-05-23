package com.zaga.model;

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
    public String date; // start date
    public String validDate; // endDate
    public String from; // companyAddress
    public String to; // clientAddress
    public String serviceDescription;
    public Float totalManDays; // changed fieldname
    public Float unitPrice; // changed fieldname
    public Currency clientCurrency;
    public Float totalAmount; // changed datatype

    public String duration; // peroid
    // public String manDays;
    public Float totalPrice;
    public Float gstAmount;
    public String employeeRole;
    public String employeeName;
    public String projectName;
    public String gstPercent;

}
