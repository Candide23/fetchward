package com.fetchAward.demo.service;

import com.fetchAward.demo.Exception.ReceiptNotFoundException;
import com.fetchAward.demo.Model.Receipt;
import com.fetchAward.demo.Repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService{

    ReceiptRepository receiptRepository;

    @Override
    public Receipt getReceipt(UUID id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        return unwrapReceipt(receipt, id);
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }


    @Override
    public void deleteReceipt(UUID id) {
        receiptRepository.deleteById(id);

    }

    @Override
    public List<Receipt> getReceipts() {
        return null;
    }

    static Receipt unwrapReceipt(Optional<Receipt> entity, UUID id) {
        if (entity.isPresent()) return entity.get();
        else throw new ReceiptNotFoundException(id);
    }

    /*ReceiptRepository receiptRepository;

    @Override
    public Receipt getReceipt(UUID id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        return unwrapReceipt(receipt, id);
    }


    @Override
    public void deleteReceipt(UUID id) {

    }

    @Override
    public List<Receipt> getReceipts() {
        return null;
    }


   */
}
