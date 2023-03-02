package com.fetchAward.demo.service;

import com.fetchAward.demo.Model.Receipt;

import java.util.List;
import java.util.UUID;



public interface ReceiptService {

    Receipt getReceiptById(UUID id);
    void saveReceipt(Receipt receipt);
    List<Receipt> getReceipts();
    int  calculatePoints(Receipt receipt);
    void storeReceipt(UUID id, int points);

}
