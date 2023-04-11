package com.zaga.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    public LocalDate date;
    public String clientAddress;
    public String payOrder;
    public String sfdc;
    public String pa;
    public String duration;
    public Float rate;
    public Float totalManDays;
    public Float manDays;
    public Float invoiceAmount;
    public Float tolaInvoiceAmount;

}
