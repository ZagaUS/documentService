package com.zaga.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditNote {

    public String creditNoteId;
    public String invoiceId;
    public String clientAddress;
    public String ref;
    public String referenceInvoice;
    public String po;
    public String sfdc;
    public String pa;
    public String currencyType;
    public Float paidAmount;
    public Float actualAmount;
    public Float creditAmount;
    public String projectId;
    public LocalDate date;
    public String projectName;
}
