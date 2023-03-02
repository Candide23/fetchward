package com.fetchAward.demo.service;

import com.fetchAward.demo.Exception.ReceiptNotFoundException;
import com.fetchAward.demo.Model.Item;
import com.fetchAward.demo.Model.Receipt;
import com.fetchAward.demo.Repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    private ReceiptRepository receiptRepository;


    @Override
    public Receipt getReceiptById(UUID id) {
        return receiptRepository.getReceipt(findIndexById(id));
    }

    @Override
    public void saveReceipt(Receipt receipt) {
        receiptRepository.saveContact(receipt);

    }

    @Override
    public List<Receipt> getReceipts() {
        return receiptRepository.getReceipts();
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = 0;
        // Rule 1: One point for every alphanumeric character in the retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        // Rule 2: 50 points if the total is a round dollar amount with no cents
        if (receipt.getTotal().equals(Math.floor(receipt.getTotal()))) {
            points += 50;
        }
        // Rule 3: 25 points if the total is a multiple of 0.25
        if (Math.abs(receipt.getTotal() - Math.round(receipt.getTotal() * 4) / 4.0) < 0.001) {
            points += 25;
        }
        // Rule 4: 5 points for every two items on the receipt
        points += 5 * receipt.getItems().size() / 2;
        // Rule 5: If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        for (Item item : receipt.getItems()) {
            int length = item.getShortDescription().trim().length();
            if (length % 3 == 0) {
                int extraPoints = (int) Math.ceil(item.getPrice() * 0.2);
                points += extraPoints;
            }
        }
        // Rule 6: 6 points if the day in the purchase date is odd
        int day = Integer.parseInt(receipt.getPurchaseDate().substring(8, 10));
        if (day % 2 == 1) {
            points += 6;
        }
        // Rule 7: 10 points if the time of purchase is after 2:00pm and before 4:00pm
        int hour = Integer.parseInt(receipt.getPurchaseTime().substring(0, 2));
        if (hour > 14 && hour < 16) {
            points += 10;
        }
        return points;
    }

    @Override
    public void storeReceipt(UUID id, int points) {


    }


    private int findIndexById(UUID id) {
        return IntStream.range(0, receiptRepository.getReceipts().size())
                .filter(index -> receiptRepository.getReceipts().get(index).getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ReceiptNotFoundException(id));
    }
}
