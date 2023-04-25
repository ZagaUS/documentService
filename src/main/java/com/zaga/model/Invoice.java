package com.zaga.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    // dyn
    public String invoiceId;
    // dyn
    public LocalDate date;
    public String clientAddress;
    // dyn
    public String projectName;
    // dyn
    public String consultant;
    public String note;
    public String payOrder;
    public String sfdc;
    public String pa;
    public Float totalManDays;
    // dyn duration
    public Float manHours;
    public Float invoiceAmount;
    public Float totalInvoiceAmount;

    public String projectId;
    public LocalDate startDate;
    public LocalDate endDate;

}
