package com.fetchAward.demo.service;

import com.fetchAward.demo.Model.Receipt;

import java.util.List;
import java.util.UUID;



public interface ReceiptService {

    Receipt getReceipt(UUID id);
    Receipt   saveReceipt(Receipt receipt);
        void deleteReceipt(UUID id);
        List<Receipt> getReceipts();

}
