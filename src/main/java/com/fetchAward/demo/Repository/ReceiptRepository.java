package com.fetchAward.demo.Repository;


import com.fetchAward.demo.Model.Receipt;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ReceiptRepository {



    private List<Receipt> receipts = new ArrayList<>();

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Receipt getReceipt(int index) {
        return receipts.get(index);
    }

    public void saveContact(Receipt receipt) {
        receipts.add(receipt);
    }

    public void storeReceipt(UUID id, int points) {
        System.out.println("Stored receipt " + id + " with " + points + " points");
    }

}
