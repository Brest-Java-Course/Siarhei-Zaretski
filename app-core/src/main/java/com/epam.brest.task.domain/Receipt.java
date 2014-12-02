package com.epam.brest.task.domain;

import org.joda.time.LocalDateTime;

/**
 * Created by sphincs on 1.12.14.
 */

public class Receipt {

    private Long receiptId;
    private LocalDateTime receiptDate;
    private Long inputMoney;
    private Long outputMoney;

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setInputMoney(Long inputMoney) {
        this.inputMoney = inputMoney;
    }

    public Long getInputMoney() {
        return inputMoney;
    }

    public void setOutputMoney(Long outputMoney) {
        this.outputMoney = outputMoney;
    }

    public Long getOutputMoney() {
        return outputMoney;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }
}
