package com.fetchAward.demo.Exception;

import java.util.UUID;

public class ReceiptNotFoundException extends RuntimeException {

    public ReceiptNotFoundException(UUID id) {
        super("The Receipt id" + id + "" + "does not exist in our records");
    }
}
